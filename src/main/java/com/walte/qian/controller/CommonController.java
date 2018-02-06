package com.walte.qian.controller;

import com.jfinal.core.Controller;
import com.walte.qian.annotation.UrlMapper;

@UrlMapper(val = "/")
public class CommonController extends Controller {

    public void index () {
        render ( "html/index.html" );
    }

}
