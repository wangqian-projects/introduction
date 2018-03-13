package com.walte.qian.controller;

import com.jfinal.core.Controller;
import com.walte.qian.annotation.UrlMapper;

/**
 * <p>ClassName: ProjectsController</p>
 * <p>Description: </p>
 * 
 * @author wangqian
 * @date 2018-03-12 18:03
 */
@UrlMapper("/projects/")
public class ProjectsController extends Controller {

    public void index(){
        render ( "/html/main/page/projects/projects.html" );
    }

}
