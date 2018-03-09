package com.walte.qian.dac.entity;

import com.walte.qian.dac.annotation.DBColumn;
import com.walte.qian.dac.annotation.DBEntity;

/**
 * <p>ClassName: DataVersion</p>
 * <p>Description: 数据版本管理实现类</p>
 *
 * @author wangqian
 * @date 2018-03-08 17:58
 */
@DBEntity(tableName = "sys_DataVersion", uuid = "f6351d70-6b42-48a4-9421-9eab05133270", finalEntity = true, version = "1.0")
public class DataVersion {

    @DBColumn(comment = "标识", primaryKey = true, size = 150)
    private String key;

    @DBColumn(comment = "版本号")
    private long versionNum;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(long versionNum) {
        this.versionNum = versionNum;
    }
}
