package org.wangqian.introduction.tools.sys;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean isNotEmpty(String str) {
        return !isStrEmpty(str);
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

    /**
     * 提取字符串
     * @param regx 正则
     * @param str 要匹配的字符串
     * @return String
     * @throws Exception 异常
     */
    public static String loadStrByRegx(String regx, String str) throws Exception {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? str.substring(matcher.start(), matcher.end()) : "";
    }


}
