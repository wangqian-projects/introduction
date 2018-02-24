package com.walte.qian.dac.annotation;

import com.walte.qian.dac.enums.TableType;
import com.walte.qian.dac.service.spi.IInnerEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ClassName: DBEntity</p>
 * <p>Description: 实体表注解</p>
 *
 * @author wangqian
 * @date 2018-02-24 16:02
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBEntity {

    /**
     * 表注释
     *
     * @return String
     */
    String comment() default "";

    /**
     * 是否是不可增加自定义的实体类
     *
     * @return boolean
     */
    boolean finalEntity() default false;

    /**
     * 内置成员
     *
     * @return Class<?                               extends                               IInnerEnum>
     */
    Class<? extends IInnerEnum> innerData() default IInnerEnum.class;

    /**
     * 表名称<br>
     * 如果为空, 则使用tableName
     *
     * @return String
     */
    String name() default "";

    /**
     * 重构表版本号<br>
     * 表中没有数据时, 才执行重构
     *
     * @return long
     */
    long rebuildVer() default 0;

    /**
     * 是(多系统)共享表
     * 如果不存在, 则创建, 否则, 不自动结构同步, 仅更新元数据
     *
     * @return boolean
     */
    boolean sheared() default false;

    /**
     * 数据库表名
     *
     * @return String
     */
    String tableName();

    /**
     * 表类型: 系统表\ 业务表
     *
     * @return TableType
     */
    TableType tableType() default TableType.BUSINESS;

    /**
     * 表的UUID
     *
     * @return String
     */
    String uuid();

    /**
     * 版本字符, 用于对比判断是否需要重构数据库表<br>
     * 如果表名、字段名等发生变化，需要变更版本号以提示系统需要重构表，且版本号不能重复<br>
     * 版本号可以分段, 例如每段是数字<br>
     * 版本检查时只有版本号大于数据库版本号时, 才做数据表同步<br>
     *
     * @return String
     */
    String version();

    /**
     * 页面是否可见, 默认不见
     *
     * @return boolean
     */
    boolean visable() default false;


}
