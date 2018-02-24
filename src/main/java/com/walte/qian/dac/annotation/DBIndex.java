package com.walte.qian.dac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBIndex</p>
 * <p>Description: 索引注解</p>
 *
 * @author wangqian
 * @date 2018-02-24 16:34
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBIndex {

    /**
     * 索引名称<br>
     * 默认取以column名称, 可以",", 相同名字的会组成联合索引
     *
     * @return boolean
     */
    String indexName() default "";

    /**
     * 是否是唯一索引
     *
     * @return boolean
     */
    boolean unique() default false;
}
