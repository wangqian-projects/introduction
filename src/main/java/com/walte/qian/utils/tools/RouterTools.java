package com.walte.qian.utils.tools;

import com.jfinal.config.Routes;
import com.walte.qian.annotation.UrlMapper;

/**
 * 路由工具类
 */
public class RouterTools {

    @SuppressWarnings("unchecked")
    public void loadController(Routes routes, String basePackage) {
        try {
            //使用包路径扫描包下的类
            ScanTools scan = new ScanTools();
            scan.loadClassFile(basePackage);
            UrlMapper con = null;
            for (Class<?> clzz : scan.getClassList()) {
                con = clzz.getDeclaredAnnotation(UrlMapper.class);
                if (con != null) {
                    routes.add(con.val(), (Class<? extends com.jfinal.core.Controller>) clzz);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
