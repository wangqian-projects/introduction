package com.walte.qian.notes.cache;

import sun.security.util.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本地缓存
 *
 * @author 王骞
 * @date 2019-10-25
 */
public class LocalCache<K, V> extends Cache<K, V> {

    /**
     * 真正存储数据的Map，使用ConcurrentHashMap
     */
    private final Map<K, Element<V>> cache;

    /**
     * 默认最大缓存对象数
     */
    private static final int DEFAULT_MAX_NUMBER = 1000;

    /**
     * 默认过期时间
     */
    private static final long DEFAULT_EXPIRE = 100;

    /**
     * 最大对象数
     */
    private final int maxNumber;

    /**
     * 并发控制器，很重要，防止高并发下本地缓存对象个数超过maxNumber
     */
    private final AtomicInteger cur = new AtomicInteger(0);

    /**
     * 使用默认最大对象数100
     */
    public LocalCache() {
        this(DEFAULT_MAX_NUMBER);
    }

    public LocalCache(int maxNumber) {
        this.maxNumber = maxNumber;
        this.cache = new ConcurrentHashMap<>(maxNumber);
    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public void clear() {
        for (Map.Entry<K, Element<V>> entry : cache.entrySet()) {
            removeValue(entry.getKey());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        LocalCache localCache = new LocalCache();
        // 线程数
        int n = 500;
        // 每个线程put个数
        int m = 1000;
        CountDownLatch count = new CountDownLatch(n);
        AtomicInteger io = new AtomicInteger();
        for (int i = 0; i < n; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < m; j++) {
                    localCache.put(j+"/i" + "", "线程:" + finalI, 10);
                    System.out.println("取: " + localCache.get(j+"/i"));
                    io.addAndGet(1);
                }
                count.countDown();
            }).start();
        }
        count.await();

        localCache.k();
        System.out.println(io.get());
        System.out.println("size:" + localCache.cache.size());
        System.out.println("cur:" + localCache.cur);
        System.out.println("耗时  " + (System.currentTimeMillis() - start));

    }

    private void k() {
        cache.forEach((k, vElement) -> {
            System.out.println("K:" + k + "->" + "V:" + vElement.value);
        });
    }

    @Override
    public void put(K k, V v) {
        put(k, v, DEFAULT_EXPIRE);
    }

    public boolean put(K key, V value, long expire) {
        if (key == null || value == null || expire < 0) {
            System.out.println("本地缓存put参数异常");
            return false;
        }

        //如果CAS增加失败直接返回添加失败
        if (!incr()) {
            return false;
        }

        //判断是否需要过期
        if (isOver()) {
            //触发一次全量过期
            expireAll();
            //二次检查
            if (isOver()) {
                System.out.println("本地缓存put时全量过期后还没有空间");
                decr();
                return false;
            }
        }
        putElem(key, value, expire);
        return true;
    }

    @Override
    public V get(Object key) {
        Element<V> vElement = cache.get(key);

        if (vElement == null) {
            return null;
        }
        if (isExpired(vElement)) {
            System.out.println("本地缓存key={"+ key +"}已经过期");
            removeValue(key);
            return null;
        }
        return vElement.value;
    }

    @Override
    public void remove(Object o) {

    }

    @Override
    public void setCapacity(int i) {

    }

    @Override
    public void setTimeout(int i) {

    }

    @Override
    public void accept(CacheVisitor<K, V> cacheVisitor) {

    }

    private boolean isOver() {
        return cur.get() > maxNumber;
    }

    /**
     * 扫描所有的对象对需要过期的过期
     */
    private void expireAll() {
        System.out.println("开始过期本地缓存");
        for (Map.Entry<K, Element<V>> entry : cache.entrySet()) {
            if (isExpired(entry.getValue())) {
                removeValue(entry.getKey());
            }
        }
    }

    /**
     * 判断是否过期，实现很简单
     */
    private boolean isExpired(Element element) {
        long current = System.currentTimeMillis();
        return current - element.updateTime > element.expire;
    }

    private void removeValue(Object key) {
        //真正删除成功了  使得cur和map的size统一
        if (cache.remove(key) != null) {
            decr();
        }
    }


    /**
     * 为了保证cur和Map的size时刻保持一致这里我查询了put的注释及ConcurrentHashMap底层关于put的实现。
     * 发现如果put方法返回的不是null说明存在覆盖操作，如果是覆盖那么Map的size其实没有变，因为我们添加之前把cur的值增加
     * 上去了所以要在这里减下来。
     *
     * @param key
     * @param value
     * @param expire
     */
    private void putElem(K key, V value, long expire) {
        Element<V> vElement = new Element<>(value, System.currentTimeMillis(), expire);
        if (cache.put(key, vElement) != null) {
            decr();
        }
    }

    private boolean incr() {
        int c = cur.get();
        return cur.compareAndSet(c, ++c);
    }

    /**
     * 因为CAS不一定是一定成功的
     * 所以这里通过循环保证成功
     */
    private void decr() {
        for (; ; ) {
            int c = cur.get();
            if (c == 0) {
                System.out.println("LocalCache decr cur is 0");
                return;
            }
            if (cur.compareAndSet(c, --c)) {
                return;
            }
        }
    }

    private static class Element<V> {

        /**
         * 存储对象
         */
        private V value;
        /**
         * 更新时间
         */
        private long updateTime;

        /**
         * 有效期
         */
        private long expire;


        public Element(V value, long updateTime, long expire) {
            this.value = value;
            this.updateTime = updateTime;
            this.expire = expire;
        }
    }
}
