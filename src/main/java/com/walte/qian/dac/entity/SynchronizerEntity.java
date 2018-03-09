package com.walte.qian.dac.entity;

import com.walte.qian.dac.annotation.DBColumn;
import com.walte.qian.dac.annotation.DBEntity;

import java.util.UUID;

/**
 * <p>ClassName: SynchronizerEntity</p>
 * <p>Description: 数据库元数据存储表</p>
 * 
 * @author wangqian
 * @date 2018-03-09 10:16
 */
@DBEntity(tableName = "sys_Synchronizer", uuid = "5df3d198-01cb-4b5f-ba3c-fc8fded4ab27", finalEntity = true, version = "1.0")
public class SynchronizerEntity {

    @DBColumn(primaryKey = true, comment = "主键")
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
