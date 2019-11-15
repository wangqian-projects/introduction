package org.wangqian.introduction.configure.config.mybatisplus;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源决策
 *
 * @author 王骞
 * @date 2019-11-05
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }

}
