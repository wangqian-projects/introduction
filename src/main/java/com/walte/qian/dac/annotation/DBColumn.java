package com.walte.qian.dac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBColumn</p>
 * <p>Description: 数据库字段注解</p>
 *
 * @author wangqian
 * @date 2018-02-07 10:57
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBColumn {

    /**
     * 数据库列名
     *
     * @return String
     */
    String column() default "";

    /**
     * 注释
     *
     * @return String
     */
    String comment();

    /**
     * 默认值
     *
     * @return String
     */
    String defaultValue() default "";

    /**
     * 小数数位
     *
     * @return int
     */
    int decimal() default 8;

    /**
     * 字符串(String)默认100, -1表示Clob(大文本)<br>
     * 定点数(BigDecimal)默认长度32, 如果设置的值<=0, 则按默认长度.
     *
     * @return
     */
    int size() default 0;

    /**
     * 是否是多语言
     *
     * @return boolean
     */
    boolean mutiLanguageColumn() default false;

    /**
     * 不能为空
     *
     * @return boolean
     */
    boolean notNull() default false;

    /**
     * 是否是主键
     *
     * @return boolean
     */
    boolean primaryKey() default false;

    /**
     * 是否是系统字段
     *
     * @return boolean
     */
    boolean sysField() default true;

    /**
     * 是否是版本字段
     *
     * @return
     */
    boolean versionColum() default false;


}
