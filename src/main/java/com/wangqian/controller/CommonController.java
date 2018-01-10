package com.wangqian.controller;

import com.jfinal.core.Controller;
import com.wangqian.annotation.UrlMapper;

@UrlMapper(val = "/")
public class CommonController extends Controller {

    public void index () {
        render ( "html/index.html" );
    }

}
