package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigExact {

    /**
     * 拓展配置
     */
    private static Map<String, String> extendsConfig = new HashMap<String, String>();

    /**
     * 是否显示SQL
     */
    private static final boolean showSql = true;

    /**
     * 数据库集
     */
    private static List<Map<String, String>> dbs = new ArrayList<Map<String, String>>();

    /**
     * 数据库00
     */
    private static Map<String, String> db_00 = new HashMap<String, String>();

    /**
     * 数据库01
     */
    private static Map<String, String> db_01 = new HashMap<String, String>();

    /**
     * 路由包名集
     */
    private static List<String> routesPackage = new ArrayList<>();

    /**
     * 数据中心标识(DATACENTER_ID)和机器工作标识(WORKER_ID), 不许超过31
     * 数据全局ID服务参数
     */
    private static final long WORKER_ID = 12;
    private static final long DATACENTER_ID = 13;

    static {
        routesPackage.add("com.wangqian.controller");
    }

    static {
        db_00.put("name", "51ykb");
        db_00.put("type", "mysql");
        db_00.put("className", "com.mysql.jdbc.Driver");
        db_00.put("jdbcUrl", "jdbc:mysql://localhost:3306/51ykb?useUnicode=true&characterEncoding=utf-8");
        db_00.put("userName", "root");
        db_00.put("password", "123456");
        db_00.put("minPoolSize", "100");
        db_00.put("maxPoolSize", "150");
        db_00.put("idleConnectionTestPeriod", "60");
        dbs.add(db_00);
    }

    static {
        db_01.put("name", "51ykb01");
        db_01.put("type", "mysql");
        db_01.put("className", "com.mysql.jdbc.Driver");
        db_01.put("jdbcUrl", "jdbc:mysql://localhost:3306/51ykb?useUnicode=true&characterEncoding=utf-8");
        db_01.put("userName", "root");
        db_01.put("password", "123456");
        db_01.put("minPoolSize", "100");
        db_01.put("maxPoolSize", "150");
        db_01.put("idleConnectionTestPeriod", "60");
//        dbs.add(db_01);
    }


    public static Map<String, String> getExtendsConfig() {
        return extendsConfig;
    }

    public static boolean isShowSql() {
        return showSql;
    }

    public static List<Map<String, String>> getDbs() {
        return dbs;
    }

    public static List<String> getRoutesPackage() {
        return routesPackage;
    }

    public static long getWorkerId() {
        return WORKER_ID;
    }

    public static long getDatacenterId() {
        return DATACENTER_ID;
    }
}
