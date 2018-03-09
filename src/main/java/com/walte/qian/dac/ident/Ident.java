package com.walte.qian.dac.ident;

import com.walte.qian.dac.enums.ColumnDataEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: Ident</p>
 * <p>Description: 业务标识声明注解</p>
 *
 * @author wangqian
 * @date 2018-03-09 15:23
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ident {

    /**
     * 改业务标识, 支持的数据类型
     *
     * @return ColumnDataEnum[]
     */
    ColumnDataEnum[] columnDatas() default {};

    /**
     * 是否动态外键
     *
     * @return boolean
     */
    boolean dynamicFK() default false;

    /**
     * 排序
     *
     * @return float
     */
    float order();


}
