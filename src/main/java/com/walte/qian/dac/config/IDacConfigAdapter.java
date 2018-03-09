package com.walte.qian.dac.config;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.UUID;

/**
 * <p>ClassName: IDacConfigAdapter</p>
 * <p>Description: DAC服务</p>
 *
 * @author wangqian
 * @date 2018-03-09 10:51
 */
public interface IDacConfigAdapter {

    /**
     * 获取当前语言
     *
     * @return long
     */
    public long getLanguageId();

    /**
     * 获取当前版本
     *
     * @param uuid UUID
     * @return long
     */
    public long getCurrentVersion(UUID uuid);


    /**
     * 更新版本
     *
     * @param uuid UUID
     */
    public void upVersion(UUID uuid);


    /**
     * 获取服务
     *
     * @param cls Class
     * @param <T> 泛型
     * @return T
     */
    public <T> T getService(Class<?> cls);


    /**
     * debug日志
     *
     * @param str String
     */
    public void debug(String str);


    /**
     * info日志
     *
     * @param str
     */
    public void info(String str);


    /**
     * 错误日志
     *
     * @param message 信息
     * @param e       Exception
     */
    public void error(String message, Exception e);


    /**
     * 根据注解获取class
     *
     * @param cls Class extends Annotation
     * @return
     */
    public List<Class<?>> getClasses(Class<? extends Annotation> cls);


    /**
     * 获取语言信息
     *
     * @param dac DAC
     * @param key KEY
     * @return String
     */
    public String t(String dac, String key);


    /**
     * 开启一个新链接
     *
     * @throws Exception 异常
     */
    public void newConnection() throws Exception;


    /**
     * 恢复到上一个链接
     */
    public void recoverConnection();


}
