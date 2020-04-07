package org.wangqian.introduction.configure.annotaition;

/**
 * 数据库选择
 *
 * @author 王骞
 * @date 2019-11-05
 */

import org.wangqian.introduction.configure.enums.DBTypeEnum;

import java.lang.annotation.*;

/**
 * The core Annotation to switch datasource. It can be annotate at class or method.
 *
 * @author TaoYu Kanyuxia
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DB {

    /**
     * groupName or specific database name or spring SPEL name.
     *
     * @return the database you want to switch
     */
    DBTypeEnum value() default DBTypeEnum.DB_P;

}
