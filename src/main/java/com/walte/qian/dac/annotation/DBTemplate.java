package com.walte.qian.dac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBTemplate</p>
 * <p>Description: 表模板注解</p>
 *
 * @author wangqian
 * @date 2018-02-24 16:45
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTemplate {

    /**
     * 模板说明注释
     *
     * @return String
     */
    String comment() default "";

    /**
     * 模板名称
     *
     * @return String
     */
    String name();

    /**
     * 模板UUID
     *
     * @return String
     */
    String uuid();

    /**
     * 版本号
     *
     * @return String
     */
    String version();

    /**
     * 页面是否可见<br>
     * 默认可见
     *
     * @return boolean
     */
    boolean visable() default true;

}
