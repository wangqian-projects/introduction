package org.wangqian.introduction.tools.utils.param;

import cn.hutool.core.map.MapBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 链式Map创建类
 *
 * @author 王骞
 * @date 2019-09-26
 */
public class ParamMapBuilder<K, V> extends MapBuilder<K, V> {

    /**
     * 链式Map创建类
     *
     * @param map 要使用的Map实现类
     */
    public ParamMapBuilder(Map<K, V> map) {
        super(map);
    }

    /**
     * 链式Map创建类
     *
     */
    private ParamMapBuilder() {
        super(new HashMap<>());
    }

    /**
     * 链式Map创建类
     * @param size 预计 map初始大小
     */
    private ParamMapBuilder(int size) {
        super(new HashMap<>((int) (size / 0.75) + 1));
    }

    /**
     * 创建链接调用map
     *
     * @return map创建类
     */
    public static ParamMapBuilder<String, Object> builder() {
        return new ParamMapBuilder<>();
    }

    /**
     * 创建链接调用map
     * @param size 估计map初始大小
     * @return map创建类
     */
    public static ParamMapBuilder<String, Object> builder(int size) {
        return new ParamMapBuilder<>(size);
    }

    /**
     * 创建链接调用map
     *
     * @param <K> Key类型
     * @param <V> Value类型
     * @param map 实际使用的map
     * @return map创建类
     */
    public static <K, V> ParamMapBuilder<K, V> builder(Map<K, V> map) {
        return new ParamMapBuilder<>(map);
    }

    @Override
    public ParamMapBuilder<K, V> put(K k, V v) {
        super.put(k, v);
        return this;
    }

    /**
     * 获取map
     *
     * @return
     */
    public Map<K, V> get() {
        return super.map();
    }

    /**
     * 清空map
     *
     * @return
     */
    public ParamMapBuilder<K, V> clear() {
        super.map().clear();
        return this;
    }
}
