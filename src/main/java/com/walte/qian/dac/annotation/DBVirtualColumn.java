package com.walte.qian.dac.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBVirtualColumn</p>
 * <p>Description: 虚字段列</p>
 *
 * @author wangqian
 * @date 2018-02-24 15:58
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBVirtualColumn {

}
