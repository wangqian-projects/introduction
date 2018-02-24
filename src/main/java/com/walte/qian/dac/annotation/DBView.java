package com.walte.qian.dac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBView</p>
 * <p>Description: 视图注解</p>
 *
 * @author wangqian
 * @date 2018-02-24 16:38
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBView {

    /**
     * 注释
     *
     * @return String
     */
    String comment() default "";

    /**
     * 指定为视图时, 必须指定原表的class, 如果没有实体class, 则指定void.class
     *
     * @return Class<?>
     */
    Class<?> tableClass();

    /**
     * 表名
     *
     * @return String
     */
    String tableName() default "";

}
