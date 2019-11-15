package org.wangqian.introduction.tools.utils;


import cn.hutool.core.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.wangqian.introduction.tools.support.Convert;


@Component
public class GetEnvironmentUtil {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     *
     * @param envString
     * @return
     */
    public String getEnv(String envString){
        Environment environment = applicationContext.getEnvironment();
        return environment.getProperty(envString,"");
    }

    //是否发送短信
    public boolean sendMsg(){
        String env = getEnv("system.sendMes.status");
        Assert.notBlank(env,"置项不能为空");
        return Boolean.valueOf(env);
    }

    //请求是否加密
    public boolean getRequestJM(){
        String env = getEnv("system.request.encryption");
        Assert.notBlank(env,"置项不能为空");
        return Boolean.valueOf(env);
    }

    //响应是否加密
    public boolean getResponseJM(){
        String env = getEnv("system.response.encryption");
        Assert.notBlank(env,"置项不能为空");
        return Boolean.valueOf(env);
    }

    //响应是否加密
    public boolean getQuartz(){
        String env = getEnv("system.quartz.status");
        Assert.notBlank(env,"置项不能为空");
        return Boolean.valueOf(env);
    }

    //创蓝api账号
    public String clApiAccount(){
        String env = getEnv("system.chuanglang.account");
        Assert.notBlank(env,"创蓝api账号不能为空");
        return env;
}

    //创蓝密码
    public String clPwd(){
        String env = getEnv("system.quartz.pwd");
        Assert.notBlank(env,"置项不能为空");
        return env;
    }

    //创蓝地址
    public String clUrl(){
        String env = getEnv("system.quartz.url");
        Assert.notBlank(env,"置项不能为空");
        return env;
    }

    public String clReport(){
        String env = getEnv("system.quartz.report");
        Assert.notBlank(env,"置项不能为空");
        return env;
    }

    //获取环境
    public String active(){
        String env = getEnv("spring.profiles.active");
        Assert.notBlank(env,"置项不能为空");
        return env;
    }

    //获取http地址
    public String httpUrl(){
        String env = getEnv("system.http.url");
        Assert.notBlank(env,"置项不能为空");
        return env;
    }

    //超时时间
    public Integer httpTimeOut(){
        String env = getEnv("system.http.timeout");
        Assert.notBlank(env,"置项不能为空");
        return Convert.toInt(env);
    }

}
