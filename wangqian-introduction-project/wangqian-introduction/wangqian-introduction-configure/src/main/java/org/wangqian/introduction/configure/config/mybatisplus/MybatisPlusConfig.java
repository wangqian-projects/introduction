package org.wangqian.introduction.configure.config.mybatisplus;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.wangqian.introduction.configure.enums.DBTypeEnum;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author mz
 * @Title: MybatisPlusConfig
 * @ProjectName lsj_freight
 * @Description: TODO
 * @date 2019/10/2819:37
 */
@EnableTransactionManagement
@Configuration
@MapperScan({"com.wangqian.**.dao"})
public class MybatisPlusConfig {

    @Autowired
    private MybatisPlusProperties properties;

    @Autowired
    private MybatisPlusAutoConfiguration configuration;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }

    //乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean(name = "dbp")
    @ConfigurationProperties(prefix = "spring.datasource.druid.dbp")
    public DataSource dbp() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dbbase")
    @ConfigurationProperties(prefix = "spring.datasource.druid.dbbase")
    public DataSource dbbeat() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dbpo")
    @ConfigurationProperties(prefix = "spring.datasource.druid.dbpo")
    public DataSource dbpo() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("dbp") DataSource dbp,
                                         @Qualifier("dbbase") DataSource dbbase,
                                         @Qualifier("dbpo") DataSource dbpo) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.DB_P.getValue(), dbp);
        targetDataSources.put(DBTypeEnum.DB_BASE.getValue(), dbbase);
        targetDataSources.put(DBTypeEnum.DB_PO.getValue(), dbpo);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dbp);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(multipleDataSource(dbp(), dbbeat(), dbpo()));
        setMybatisPlusFactory(sqlSessionFactoryBean);
        return sqlSessionFactoryBean.getObject();
    }

    private void setMybatisPlusFactory(MybatisSqlSessionFactoryBean factory) throws Exception {
        Class<?> superclass = configuration.getClass().getSuperclass();
        Field[] declaredFields = superclass.getDeclaredFields();
        // 需要反射获取的字段
        ResourceLoader resourceLoader = null;
        Interceptor[] interceptors = null;
        DatabaseIdProvider databaseIdProvider =null;
        TypeHandler[] typeHandlers = null;
        LanguageDriver[] languageDrivers = null;
        ApplicationContext applicationContext = null;
        List<ConfigurationCustomizer> configurationCustomizers = null;
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            declaredField.setAccessible(true);
            String name = declaredField.getName();
            switch (name) {
                case "properties":
                    break;
                case "interceptors":
                    interceptors = (Interceptor[]) declaredField.get(configuration);
                    break;
                case "typeHandlers":
                    typeHandlers = (TypeHandler[]) declaredField.get(configuration);
                    break;
                case "languageDrivers":
                    languageDrivers = (LanguageDriver[]) declaredField.get(configuration);
                    break;
                case "resourceLoader":
                    resourceLoader = (ResourceLoader)declaredField.get(configuration);
                    break;
                case "databaseIdProvider":
                    databaseIdProvider = (DatabaseIdProvider) declaredField.get(configuration);
                    break;
                case "configurationCustomizers":
                    configurationCustomizers = (List<ConfigurationCustomizer>)declaredField.get(configuration);
                    break;
                case "mybatisPlusPropertiesCustomizers":
                    break;
                case "applicationContext":
                    applicationContext = (ApplicationContext) declaredField.get(configuration);
                    break;
                default:
                    break;
            }
        }
        // 复原MybatisPlusAutoConfiguration配置
        // TODO 使用 MybatisSqlSessionFactoryBean 而不是 SqlSessionFactoryBean

        // factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation()) && resourceLoader != null) {
            factory.setConfigLocation(resourceLoader.getResource(this.properties.getConfigLocation()));
        }

        // applyConfiguration(factory);
        // TODO 使用 MybatisConfiguration
        MybatisConfiguration configuration = this.properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
            configuration = new MybatisConfiguration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : configurationCustomizers) {
                customizer.customize(configuration);
            }
        }
        factory.setConfiguration(configuration);

        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(interceptors)) {
            factory.setPlugins(interceptors);
        }
        if (databaseIdProvider != null) {
            factory.setDatabaseIdProvider(databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (this.properties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(typeHandlers)) {
            factory.setTypeHandlers(typeHandlers);
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }

        // TODO 对源码做了一定的修改(因为源码适配了老旧的mybatis版本,但我们不需要适配)
        Class<? extends LanguageDriver> defaultLanguageDriver = this.properties.getDefaultScriptingLanguageDriver();
        if (!ObjectUtils.isEmpty(languageDrivers)) {
            factory.setScriptingLanguageDrivers(languageDrivers);
        }
        Optional.ofNullable(defaultLanguageDriver).ifPresent(factory::setDefaultScriptingLanguageDriver);

        // TODO 自定义枚举包
        if (StringUtils.hasLength(this.properties.getTypeEnumsPackage())) {
            factory.setTypeEnumsPackage(this.properties.getTypeEnumsPackage());
        }
        // TODO 此处必为非 NULL
        GlobalConfig globalConfig = this.properties.getGlobalConfig();
        // TODO 注入填充器
        if (applicationContext != null && applicationContext.getBeanNamesForType(MetaObjectHandler.class,
                false, false).length > 0) {
            MetaObjectHandler metaObjectHandler = applicationContext.getBean(MetaObjectHandler.class);
            globalConfig.setMetaObjectHandler(metaObjectHandler);
        }
        // TODO 注入主键生成器
        if (applicationContext != null && applicationContext.getBeanNamesForType(IKeyGenerator.class, false,
                false).length > 0) {
            IKeyGenerator keyGenerator = applicationContext.getBean(IKeyGenerator.class);
            globalConfig.getDbConfig().setKeyGenerator(keyGenerator);
        }
        // TODO 注入sql注入器
        if (applicationContext != null && applicationContext.getBeanNamesForType(ISqlInjector.class, false,
                false).length > 0) {
            ISqlInjector iSqlInjector = applicationContext.getBean(ISqlInjector.class);
            globalConfig.setSqlInjector(iSqlInjector);
        }
        // TODO 设置 GlobalConfig 到 MybatisSqlSessionFactoryBean
        factory.setGlobalConfig(globalConfig);
    }
}
