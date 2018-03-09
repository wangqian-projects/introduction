package com.walte.qian.dac.config;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.UUID;

/**
 * <p>ClassName: DacConfig</p>
 * <p>Description: DAC配置</p>
 *
 * @author wangqian
 * @date 2018-03-09 11:05
 */
public class DacConfig {

    private static IDacConfigAdapter adapter;

    public static void config(IDacConfigAdapter iDacService) {
        adapter = iDacService;
    }

    public static long getLanguageId() {
        return adapter.getLanguageId();
    }

    public static long getCurrentVersion(UUID uuid) {
        return adapter.getCurrentVersion(uuid);
    }

    public static void upVersion(UUID uuid) {
        adapter.upVersion(uuid);
    }

    public static <T> T getService(Class<?> cls) {
        return adapter.getService(cls);
    }

    public static void debug(String str) {
        adapter.debug(str);
    }

    public static void info(String str) {
        adapter.info(str);
    }

    public static void error(String message, Exception e) {
        adapter.error(message, e);
    }

    public static List<Class<?>> getClasses(Class<? extends Annotation> cls) {
        return adapter.getClasses(cls);
    }

    public static String t(String dac, String key) {
        return adapter.t(dac, key);
    }

    public static void newConnection() throws Exception {
        adapter.newConnection();
    }

    public static void recoverConnection() {
        adapter.recoverConnection();
    }


}
