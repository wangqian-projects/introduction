package com.walte.qian.dac.annotation;


import com.walte.qian.dac.enums.FkType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBForeignKey</p>
 * <p>Description: 外键注解</p>
 *
 * @author wangqian
 * @date 2018-02-24 15:40
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBForeignKey {

    /**
     * 外键类型
     *
     * @return FkType
     */
    FkType fkType();

    /**
     * 关联表的Entity
     *
     * @return Class<?>
     */
    Class<?> relationEntity();

    /**
     * 是否是动态外键
     *
     * @return boolean
     */
    boolean dynamicFk() default false;

    /**
     * 是否强关联
     *
     * @return boolean
     */
    boolean mustJoin() default true;

    /**
     * 关联条件组<br>
     * 当有关联条件需要多个时, 用相同的group进行分组
     *
     * @return String
     */
    String group() default "";

    /**
     * 指定关联关系所需要关联的字段<br>
     * 为空时默认为主键, 如果不为空, 关联的时候需要校验关联字段类型是否匹配
     *
     * @return String
     */
    String relationField() default "";

    /**
     *关联其他表的表名
     * @return String
     */
    String relationTable() default "";

    /**
     * 关联表结构UUID
     * @return String
     */
    String relationUuid() default "";
}
