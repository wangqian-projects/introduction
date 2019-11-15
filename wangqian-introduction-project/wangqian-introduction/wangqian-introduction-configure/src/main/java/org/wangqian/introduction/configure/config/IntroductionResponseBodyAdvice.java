package org.wangqian.introduction.configure.config;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.wangqian.introduction.tools.utils.AES;
import org.wangqian.introduction.tools.utils.GetEnvironmentUtil;
import org.wangqian.introduction.tools.utils.JsonResult;

/**
 * 返回处理
 *
 * @author 王骞
 * @date 2019-11-06
 */
@ControllerAdvice
public class IntroductionResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Autowired
    private GetEnvironmentUtil getEnvironmentUtil;

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //进行

        if(!getEnvironmentUtil.getResponseJM()){
            return o;
        }

        if (o instanceof JsonResult){
            JsonResult convert = Convert.convert(JsonResult.class, o);
            int code = convert.getCode();
            if ( 200 == code){
                if (convert.getData() != null){
                    String jm = AES.JM(JSON.toJSONString(convert.getData(), SerializerFeature.WriteMapNullValue));
                    convert.setData(jm);
                }
                return convert;
            }
        }
        return o;
    }
}
