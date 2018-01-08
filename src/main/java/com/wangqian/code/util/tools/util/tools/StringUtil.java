package com.wangqian.code.util.tools.util.tools;

public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 入参
     * @return null:true; "":true; "  ":false
     */
    public static boolean isStrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 字符串null转换
     * @param str String
     * @return String
     */
    public static String nvl(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * 字符串null转换
     * @param obj Object
     * @return String
     */
    public static String nvl(Object obj) {
        return obj == null ? "" : obj.toString();
    }

}
