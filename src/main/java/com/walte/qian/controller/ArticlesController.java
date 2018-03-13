package com.walte.qian.controller;

import com.jfinal.core.Controller;
import com.walte.qian.annotation.UrlMapper;

/**
 * <p>ClassName: ArticlesController</p>
 * <p>Description: </p>
 * 
 * @author wangqian
 * @date 2018-03-12 18:50
 */
@UrlMapper("/articles/")
public class ArticlesController extends Controller {

    public void index(){
        render ( "/html/main/page/articles/articles.html" );
    }


}
