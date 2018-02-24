package com.walte.qian.dac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBInnerData</p>
 * <p>Description: 内置数据</p>
 *
 * @author wangqian
 * @date 2018-02-24 16:43
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBInnerData {

    /**
     * 表的实体类
     *
     * @return Class<?>
     */
    Class<?> tableClass();
}
