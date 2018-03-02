package com.walte.qian.controller;

import com.jfinal.core.Controller;
import com.walte.qian.annotation.UrlMapper;
import com.walte.qian.service.UUIDBizService;

import java.util.List;

/**
 * <p>ClassName: UUIDController</p>
 * <p>Description: UUID 控制</p>
 * 
 * @author wangqian
 * @date 2018-02-27 11:49
 */
@UrlMapper("/uuidBiz")
public class UUIDBizController extends Controller{

    private UUIDBizService service = new UUIDBizService();

    public void uuidOnline() {
        setAttr("uuid",service.generateUuid("4"));
        render("/html/main/page/uuid-online.html");
    }

    public void generateUuid() {
        renderText(service.generateUuid(getPara("version")));
    }

    public void generateBulkUuid() {
        String version = getPara("version");
        int howMany = Integer.parseInt(getPara("howmany"));
        String hyphen = getPara("hyphen");
        System.out.println(version + "][" + howMany + "][" + hyphen);
        List<String> uuids = service.generateBulkUuid(version, howMany, hyphen);
        renderJson(uuids);
    }
    
}
