package org.wangqian.introduction.configure.config;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wangqian.introduction.tools.exception.BusinessException;
import org.wangqian.introduction.tools.utils.GetEnvironmentUtil;
import org.wangqian.introduction.tools.utils.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author 王骞
 * @date 2019-11-06
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private GetEnvironmentUtil environmentUtil;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult errHandler(HttpServletRequest request, Exception e, HttpServletResponse response){
        //请求参数
        String url = request.getRequestURL().toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String,Object> result = MapUtil.newHashMap();
        String toJSONString = JSONObject.toJSONString(parameterMap, SerializerFeature.UseSingleQuotes);
        result.put("requestUrl",url);
        result.put("requestParam",toJSONString);
        result.put("errorMsg",e);

        logger.error("请求地址:{}===请求参数:{}====错误信息{}",url, toJSONString,e);

        if (Boolean.valueOf(environmentUtil.getEnv("system.error.msg"))){
          return JsonResult.error(207,e.getMessage(),result);
        }

        //业务错误异常
        if (e instanceof BusinessException){
           return JsonResult.error(207,e.getMessage());
        }
        return JsonResult.error(207,"服务器错误");
    }
}
