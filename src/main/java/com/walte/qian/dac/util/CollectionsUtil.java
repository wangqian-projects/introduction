package com.walte.qian.dac.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>ClassName: CollectionsUtil</p>
 * <p>Description: 集合辅助类</p>
 *
 * @author wangqian
 * @date 2018-02-07 11:50
 */
public class CollectionsUtil {

    /**
     * 克隆一个List
     *
     * @param list 集合
     * @param <T>  泛型
     * @return ArrayList
     */
    public static <T> List<T> cloneList(List<T> list) {
        List<T> clone = CollectionsUtil.newArrayList();
        if (list != null) {
            clone.addAll(list);
        }
        return clone;
    }

    /**
     * 获取最后一个数据
     *
     * @param list 集合
     * @param <T>  泛型
     * @return T
     */
    public static <T> T getLast(List<T> list) {
        return EmptyUtil.isEmpty(list) ? null : list.get(list.size() - 1);
    }

    /**
     * arrayList
     *
     * @param <K> 泛型
     * @return List<K>
     */
    public static <K> List<K> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * arrayList
     *
     * @param size 初始大小
     * @param <K>  Object
     * @return List<K>
     */
    public static <K> List<K> newArrayList(int size) {
        return new ArrayList<>(size);
    }

    /**
     * 线程安全的Map
     *
     * @param <K> 键
     * @param <V> 值
     * @return Map<k,v>
     */
    public static <K, V> Map<K, V> newConcurrentMap() {
        return new ConcurrentHashMap<>();
    }

    /**
     * HashMap
     *
     * @param <K> 键
     * @param <V> 值
     * @return Map<k,v>
     */
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * 定义初始容量的HashMap
     *
     * @param initialCapacity 初始容量
     * @param <K> 键
     * @param <V> 值
     * @return Map<k,v>
     */
    public static <K, V> Map<K, V> newHashMap(int initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    /**
     * 有序的HashMap
     * @param <K> 键
     * @param <V> 值
     * @return Map<K,V>
     */
    public static <K, V> Map<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * HashSet
     * @param <E> 类型
     * @return Set<E>
     */
    public static <E> Set<E> newHashSet(){
        return new HashSet<>();
    }

    /**
     * Stack
     *
     * @param <E> 类型
     * @return Stack<E>
     */
    public static <E> Stack<E> newStack() {
        return new Stack<>();
    }

    private CollectionsUtil(){}

}
