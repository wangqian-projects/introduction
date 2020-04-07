package org.wangqian.introduction.tools.utils;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;


@Component
public class JsonResult {


    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public JsonResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }


    /**
     * 成功200
     *
     * @return
     */
    public static JsonResult success() {
        return new JsonResult().setCode(200).setMessage("success");
    }


    public static JsonResult success(int code, String message) {
        return new JsonResult().setCode(code).setMessage(message);
    }

    public static JsonResult success(Object data) {

        if (data instanceof IPage) {
            IPage convert = Convert.convert(IPage.class, data);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setList(convert.getRecords());
            pageInfo.setTotal(convert.getTotal());
            pageInfo.setPageNum(Convert.toInt(convert.getCurrent()));
            pageInfo.setPageSize(Convert.toInt(convert.getSize()));
            pageInfo.setPages(Convert.toInt(convert.getPages()));
            data = pageInfo;
        }

        return new JsonResult().setCode(200).setMessage("success").setData(data);
    }

    /**
     * 失败1
     *
     * @param message
     * @return
     */
    public static JsonResult error(int code, String message) {
        return new JsonResult().setCode(code).setMessage(message);
    }

    public static JsonResult SystemError() {
        return new JsonResult().setCode(207).setMessage("服务器异常");
    }

    public static JsonResult error(int code, String message, Object data) {
        return new JsonResult().setCode(code).setMessage(message).setData(data);
    }

    public static void print(HttpServletResponse response, int code, String message) {
        //设置编码格式
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println("{\"code\":" + code + ", \"message\":\"" + message + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
