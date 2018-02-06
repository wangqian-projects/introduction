package com.walte.qian.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UrlMapper {

    /**
     * 值
     */
    public String val();
}
