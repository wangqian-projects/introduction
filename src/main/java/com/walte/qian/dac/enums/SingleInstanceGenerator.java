package com.walte.qian.dac.enums;

import com.walte.qian.dac.util.SnowflakeIdWorker;
import config.ConfigExact;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

/**
 * <p>ClassName: SingleInstanceGenerator</p>
 * <p>Description: 枚举模式生产单例</p>
 *
 * @author wangqian
 * @date 2018-01-19 17:39
 */
public enum SingleInstanceGenerator {
    SNOWFLAKE_ID_WORKER_A(0, SnowflakeIdWorker.class, ConfigExact.getWorkerId(), ConfigExact.getDatacenterId()),

    SNOWFLAKE_ID_WORKER_B(1, new SnowflakeIdWorker(ConfigExact.getWorkerId(), ConfigExact.getDatacenterId()));


    private int id;
    private Class<?> clazz;
    private Object obj;

    SingleInstanceGenerator(int id, Object obj) {
        this.id = id;
        this.obj = obj;
    }

    /**
     * 构造器 传目标构造器参数
     * @param id ID
     * @param clazz 目标类型
     * @param ConstructorParams 目标构造器参数
     */
    SingleInstanceGenerator(int id, Class<?> clazz, Object... ConstructorParams) {
        this.id = id;
        this.clazz = clazz;
        int length = ConstructorParams.length;
        boolean useNoParam = false;

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();

        //匹配构造器
        for (Constructor c : declaredConstructors) {
            int score = 0;
            //构造器参数个数
            int parameterCount = c.getParameterCount();
            if (length == parameterCount) {
                if (length == 0) {
                    useNoParam = true;
                }
                //获取参数
                Parameter[] parameters = c.getParameters();
                for (int i = 0; i < parameterCount; i++) {
                    //参数类型比较
                    Object constructorParam = ConstructorParams[i];
                    Class<?> aClass = constructorParam.getClass();
                    Class<?> type = parameters[i].getType();
                    if (aClass == type || type.isPrimitive() && BasicReferenceDataTypes.getReferenceByPrimitiveClass(type) == aClass) {
                        score++;
                    }
                }
            }

            //选择的构造器
            if (useNoParam && parameterCount == 0 || score == parameterCount) {
                try {
                    obj = c.newInstance(ConstructorParams);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public Object getSingleInstance() {
        return obj;
    }

    public int getId() {
        return id;
    }
}
