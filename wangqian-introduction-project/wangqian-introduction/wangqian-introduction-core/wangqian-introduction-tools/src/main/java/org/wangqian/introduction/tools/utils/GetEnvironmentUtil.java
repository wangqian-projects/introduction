package org.wangqian.introduction.tools.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class GetEnvironmentUtil {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * @param envString
     * @return
     */
    public String getEnv(String envString) {
        Environment environment = applicationContext.getEnvironment();
        return environment.getProperty(envString, "");
    }
}
