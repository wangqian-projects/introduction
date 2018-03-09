package com.walte.qian.dac.util;

import java.util.regex.Pattern;

/**
 * <p>ClassName: RegularUtil</p>
 * <p>Description: 正则表达式工具</p>
 *
 * @author wangqian
 * @date 2018-03-09 15:03
 */
public class RegularUtil {

    /**
     * 是否是数字 此表达式只能匹配以数字开头的字符串, 无法校验银行账户等全数字字符串
     */
    public static Pattern IsNum = Pattern.compile("\\d.*");

    /**
     * 是否只含有数字
     */
    public static Pattern IsOnlyNum = Pattern.compile("\\d+");

    /**
     * 不以数字开头
     */
    public static Pattern FirstNotNum = Pattern.compile("\\D.*");

    /**
     * 只能是字母，汉字，数字和“_”
     */
    public static Pattern RegEx = Pattern.compile("[\u4e00-\u9fa5\\w]+");

    /**
     * 只能是字母, 数字和 "-"
     */
    public static Pattern RegExNotZH = Pattern.compile("[\\w]+");

    /**
     * 只能是字母
     */
    public static Pattern Letter = Pattern.compile("[A-Za-z]+");

    /**
     * 是否包含大写字母
     */
    public static Pattern UpperLetter = Pattern.compile(".*[A-Z].*");

    /**
     * 是否包含小写字母
     */
    public static Pattern LowerLetter = Pattern.compile(".*[a-z].*");

    /**
     * 是否包含数字
     */
    public static Pattern Num = Pattern.compile(".*\\d+.*");

    /**
     * 是否包含符号
     */
    public static Pattern Symbol = Pattern.compile(".*[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？+].*");

    /**
     * UUID格式
     */
    public static Pattern RegUuid = Pattern.compile("^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$");


}
