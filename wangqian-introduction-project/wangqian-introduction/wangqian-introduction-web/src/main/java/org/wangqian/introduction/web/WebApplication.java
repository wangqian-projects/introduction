package org.wangqian.introduction.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.wangqian.introduction.tools.utils.GlobalConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动器
 *
 * @author 王骞
 * @date 2019-11-15
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {"org.wangqian.**"})
@MapperScan({"com.wangqian.**.dao"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebApplication.class);
        springApplication.run(args);
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            String port = GlobalConfig.getConfig("server.port");
            String contextPath = GlobalConfig.getConfig("server.servlet.context-path");
            String hostUrl = "http://" + host + ":" + port + contextPath + "/";
            log.info(hostUrl);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
