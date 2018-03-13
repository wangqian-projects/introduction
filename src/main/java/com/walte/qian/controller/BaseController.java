package com.walte.qian.controller;

import com.jfinal.core.Controller;
import com.walte.qian.annotation.UrlMapper;

@UrlMapper("/")
public class BaseController extends Controller {

    public void index () {
        render ( "/html/main/page/index.html" );
    }

}
