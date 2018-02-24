package com.walte.qian.dac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBRelationField</p>
 * <p>Description: 关联字段</p>
 * 
 * @author wangqian
 * @date 2018-02-24 16:52
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBRelationField {

    /**
     * 别名
     * @return String
     */
    String alias() default "";

    /**
     * 强关联, 是否冗余存储
     * @return boolean
     */
    boolean redundantStorage() default false;

    /**
     * 关联字段名称
     *
     * @return String
     */
    String refField();

    /**
     * 依赖路径
     * @return String
     */
    String relyPath();

}
