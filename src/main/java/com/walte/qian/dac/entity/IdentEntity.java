package com.walte.qian.dac.entity;

import com.walte.qian.dac.annotation.DBColumn;
import com.walte.qian.dac.annotation.DBEntity;

import java.util.UUID;

/**
 * <p>ClassName: IdentEntity</p>
 * <p>Description: id号记录表</p>
 *
 * @author wangqian
 * @date 2018-03-08 18:07
 */
@DBEntity(tableName = "sys_Ident", uuid = "bf12197e-ddc7-4048-af31-b85e9ca8a7e9", version = "1.0", finalEntity = true)
public class IdentEntity {

    @DBColumn(primaryKey = true, comment = "表的唯一标识")
    private UUID tableUuid;

    @DBColumn(comment = "种子号")
    private long seed;

    @DBColumn(comment = "表名")
    private String tableName;

    public UUID getTableUuid() {
        return tableUuid;
    }

    public void setTableUuid(UUID tableUuid) {
        this.tableUuid = tableUuid;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
