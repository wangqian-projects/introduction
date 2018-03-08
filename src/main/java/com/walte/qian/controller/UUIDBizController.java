package com.walte.qian.controller;

import com.jfinal.core.Controller;
import com.jfinal.json.Json;
import com.walte.qian.annotation.UrlMapper;
import com.walte.qian.dac.provider.IdProvider;
import com.walte.qian.dac.provider.UuidProvider;
import com.walte.qian.service.UUIDBizService;
import com.walte.qian.utils.sys.JSONUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    public void generateUuid() {
        renderText(service.generateUuid(getPara("version")));
    }

    public void generateBulkUuid() {
        String version = getPara("version");
        int howMany = Integer.parseInt(getPara("howmany"));
        String hyphen = getPara("hyphen");
        List<String> uuids = service.generateBulkUuid(version, howMany, hyphen);
        renderJson(uuids);
    }

    public void downloadBulkUuid() {
        String bulkVersion = getPara("bulkVersion");
        String fileName = bulkVersion+ "v_"+ UuidProvider.get8UUID()+".txt";
        List uuidList = JSONUtil.fromJSON(getPara("uuidList"), List.class);
        HttpServletResponse res = getResponse();
        res.setContentType("text/html; charset=UTF-8");
        res.setContentType("text/plain");
        res.setHeader("Content-disposition", "attachment;filename=" + fileName);
        ServletOutputStream out = null;
        try {
            out = res.getOutputStream();
            for (Object o : uuidList) {
                out.println(o.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        renderNull();
    }
    
}
