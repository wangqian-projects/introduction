package config;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.template.Engine;
import com.walte.qian.utils.sys.StringUtil;
import com.walte.qian.utils.tools.RouterTools;

import java.util.Map;

public class AppConfig extends JFinalConfig {

    private Map<String, String> extendsConfig;

    /**
     * 常量配置
     * <p>
     * 路由信息
     * 数据库视图信息
     * 数据库连接信息
     * 加载其他拓展信息
     *
     * @param me Constants
     */
    public void configConstant(Constants me) {
        extendsConfig = ConfigExact.getExtendsConfig();
        me.setDevMode(ConfigExact.isDevMode());
        me.setError404View("/html/main/page/friendly/404.html");
        me.setError500View("/html/main/page/friendly/505.html");
    }

    /**
     * 注册路由, 将URL与对应类匹配
     *
     * @param routes 路由
     */
    public void configRoute(Routes routes) {
        for (String basePackage : ConfigExact.getRoutesPackage()) {
            new RouterTools().loadController(routes, basePackage);
        }
    }

    public void configEngine(Engine engine) {

    }

    /**
     * 注册插件
     *
     * @param plugins 插件
     */
    @Override
    public void configPlugin(Plugins plugins) {
        //数据库插件
        C3p0Plugin cp;
        ActiveRecordPlugin actPlugin;

        for (Map<String, String> db : ConfigExact.getDbs()) {
            cp = new C3p0Plugin(db.get("jdbcUrl"), db.get("userName"), db.get("password"));
            cp.setDriverClass(db.get("className"));
            cp.setMinPoolSize(Integer.valueOf(db.get("minPoolSize")));
            cp.setMaxPoolSize(Integer.valueOf(db.get("maxPoolSize")));
            cp.setInitialPoolSize(Integer.valueOf(db.get("minPoolSize")));
            plugins.add(cp);
            actPlugin = new ActiveRecordPlugin(db.get("name"), cp);
            actPlugin.setShowSql(ConfigExact.isShowSql());
            plugins.add(actPlugin);
        }
    }

    /**
     * 注册系统拦截器
     *
     * @param interceptors 拦截器
     */
    public void configInterceptor(Interceptors interceptors) {

    }

    public void configHandler(Handlers handlers) {

    }

    /**
     * jfinal启动调用
     */
    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();

    }

    /**
     * jfinal关闭调用
     */
    @Override
    public void beforeJFinalStop() {
        super.beforeJFinalStop();
    }

}
