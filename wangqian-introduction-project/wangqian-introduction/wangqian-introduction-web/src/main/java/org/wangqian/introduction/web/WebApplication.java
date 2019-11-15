package org.wangqian.introduction.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动器
 *
 * @author 王骞
 * @date 2019-11-15
 */
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {"org.wangqian.**"})
@MapperScan({"com.wangqian.**.dao"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebApplication.class);
        springApplication.run(args);
    }

}
