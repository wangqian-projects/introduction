package org.wangqian.introduction.configure.config.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.wangqian.introduction.configure.annotaition.DB;
import org.wangqian.introduction.configure.enums.DBTypeEnum;

import java.lang.reflect.Method;

/**
 * 切面切换数据源
 *
 * @author 王骞
 * @date 2019-11-05
 */
@Component
@Order(value = -100)
@Slf4j
@Aspect
public class DataSourceSwitchAspect {

    @Pointcut("execution(* org.wangqian.*.*.dao..*.*(..))")
    private void dbAspect() {
    }


    @Before("dbAspect()")
    public void dbSwitch(JoinPoint point) {
        Signature signature = point.getSignature();
        try {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            DB annotation = AnnotationUtils.findAnnotation(method, DB.class);
            if (annotation == null) {
                // 方法无注解解析类的全局配置
                switchClassDBType(point);
            } else {
                DbContextHolder.setDbType(annotation.value());
            }
        } catch (Exception e) {
//            log.error(e.getMessage());
            DbContextHolder.setDbType(DBTypeEnum.getDefault());
        }
    }

    private void switchClassDBType(JoinPoint point) {
        Class<?>[] interfaces = point.getTarget().getClass().getInterfaces();
        for (Class<?> mapperClass : interfaces) {
            DB annotation = AnnotationUtils.findAnnotation(mapperClass, DB.class);
            if (annotation != null) {
                DbContextHolder.setDbType(annotation.value());
                return;
            }
        }
        // 使用默认
        DbContextHolder.setDbType(DBTypeEnum.getDefault());
    }

}
