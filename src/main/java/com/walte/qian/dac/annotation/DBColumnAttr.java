package com.walte.qian.dac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBColumnAttr</p>
 * <p>Description: 列属性注解</p>
 *
 * @author wangqian
 * @date 2018-02-24 14:17
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBColumnAttr {

    /**
     * 业务标识
     *
     * @return String
     */
    String bizIdent() default "";

    /**
     * 字段名称<br>
     * 默认为column展示名称
     *
     * @return String
     */
    String name();

    /**
     * 字段标题<br>
     * 没有标题的字段默认为隐藏字段
     *
     * @return String
     */
    String title() default "";

    /**
     * 查询时页面坐为隐藏列或隐藏字段
     *
     * @return boolean
     */
    boolean hidden() default false;

    /**
     * 只读属性
     *
     * @return boolean
     */
    boolean readonly() default false;


}
