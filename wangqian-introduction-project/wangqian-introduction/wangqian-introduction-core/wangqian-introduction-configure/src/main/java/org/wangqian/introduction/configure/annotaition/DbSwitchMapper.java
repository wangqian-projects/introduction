package org.wangqian.introduction.configure.annotaition;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wangqian.introduction.configure.enums.DBTypeEnum;

/**
 * 数据源可切换接口
 *
 * @author 王骞
 * @date 2019-11-06
 */
public interface DbSwitchMapper<DAO extends DbSwitchMapper<DAO, T>, T> extends BaseMapper<T> {

    ThreadLocal<DBTypeEnum> VALUE = new ThreadLocal<>();

    /**
     * 切换数据源
     *
     * @param dbTypeEnum
     * @return
     */
    default DAO switchType(DBTypeEnum dbTypeEnum) {
        VALUE.set(dbTypeEnum);
        return (DAO) this;
    }
}
