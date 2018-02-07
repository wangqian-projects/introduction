package com.walte.qian.dac.util;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * <p>ClassName: EmptyUtil</p>
 * <p>Description: 空判断工具类</p>
 *
 * @author wangqian
 * @date 2018-02-07 14:13
 */
public class EmptyUtil {

    /**
     * 判断集合为空 (List Set)
     *
     * @param collection Collection类型的对象
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断Map为空
     *
     * @param map Map类型的对象
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }


    /**
     * 判断字符串为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty() || "null".equals(str);
    }

    /**
     * 判断UUID是否为空
     *
     * @param uuid UUID
     * @return 是否为空
     */
    public static boolean isEmpty(UUID uuid) {
        return uuid == null || isEmpty(uuid.toString());
    }

    /**
     * 判断数组是否为空
     *
     * @param objects Object数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(UUID uuid) {
        return !isEmpty(uuid);
    }

    private EmptyUtil() {}

}