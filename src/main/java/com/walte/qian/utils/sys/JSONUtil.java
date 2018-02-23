package com.walte.qian.utils.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>ClassName: JSONUtil</p>
 * <p>Description: Json工具类</p>
 *
 * @author wangqian
 * @date 2018-02-23 11:16
 */
public class JSONUtil {

    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return objectMapper;
    }

    /**
     * 将json字符串反序列化成对象
     *
     * @param json  字符串
     * @param clazz 对象类型
     * @param <T>   类型
     * @return 对象
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 转换带有泛型的对象
     *
     * @param json          字符串
     * @param typeReference 泛型引用
     * @param <T>           泛型
     * @return 对象
     */
    public static <T> T fromJSON(String json, TypeReference<T> typeReference) {
        try {
            return getObjectMapper().readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Java对象序列成JSON字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    public static String toJSON(Object obj) {
        try {
            return getObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Java对象序列成JSON字符串 打印LOG时使用
     *
     * @param obj
     * @return
     */
    public static String toLog(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return toJSON(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
            return "对象可能有循环引用, 无法toLog";
        }
        return "数据转JSON异常";
    }

    /**
     * 类型尝试转化
     *
     * @param obj   对象
     * @param clazz 类型
     * @param <T>   泛型
     * @return 转化后的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromObject(Object obj, Class<T> clazz) {
        String json = JSONUtil.toJSON(obj);
        if (int.class.equals(clazz) || Integer.class.equals(clazz)) {
            double doubleValue = JSONUtil.fromObject(json, double.class);
            int intValue = (int) doubleValue;
            if (doubleValue == intValue) {
                return (T) ((Integer) intValue);
            } else {
                throw new RuntimeException(String.format("'%s'转化为Integer后精度丢失", obj));
            }

        }

        if (long.class.equals(clazz) || Long.class.equals(clazz)) {
            double doubleValue = JSONUtil.fromObject(json, double.class);
            long longValue = (long) doubleValue;
            if (doubleValue == longValue) {
                return (T) ((Long) longValue);
            } else {
                throw new RuntimeException(String.format("'%s'转化为Integer后精度缺失", obj));
            }
        }

        return JSONUtil.fromObject(json, clazz);

    }

}
