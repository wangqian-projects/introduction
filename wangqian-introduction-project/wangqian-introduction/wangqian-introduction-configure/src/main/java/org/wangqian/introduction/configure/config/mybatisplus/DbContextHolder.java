package org.wangqian.introduction.configure.config.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.wangqian.introduction.configure.enums.DBTypeEnum;

/**
 * @author 王骞
 * @date 2019-11-05
 */
@Slf4j
public class DbContextHolder {

    private static final ThreadLocal CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dbTypeEnum
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        log.info("切换到 "+dbTypeEnum.getTitle()+" 数据源...");
        CONTEXT_HOLDER.set(dbTypeEnum.getValue());
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDbType() {
        return (String) CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }

}
