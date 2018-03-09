package com.walte.qian.dac.entity;

import com.walte.qian.dac.annotation.DBColumn;
import com.walte.qian.dac.annotation.DBEntity;

import java.util.UUID;

/**
 * <p>ClassName: IndexEntity</p>
 * <p>Description: 数据库索引</p>
 *
 * @author wangqian
 * @date 2018-03-09 10:07
 */
@DBEntity(tableName = "sys_Index", uuid = "36d33900-8791-4425-b2f9-c33d27c0c509", version = "1.0", rebuildVer = 1, comment = "数据库索引")
public class IndexEntity {

    @DBColumn(comment = "索引名称", primaryKey = true)
    private String indexName;

    @DBColumn(comment = "索引所属表uuid")
    private UUID tableUuid;

    @DBColumn(comment = "class内定义索引的名称")
    private String classIndexName;

    @DBColumn(comment = "是否是唯一索引")
    private boolean unique;

    @DBColumn(comment = "索引字段名序列, 字段以“,”分割", size = 500)
    private String columns;

    @DBColumn(comment = "系统固化索引")
    private boolean sysIndex;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public UUID getTableUuid() {
        return tableUuid;
    }

    public void setTableUuid(UUID tableUuid) {
        this.tableUuid = tableUuid;
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    public void setClassIndexName(String classIndexName) {
        this.classIndexName = classIndexName;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public boolean isSysIndex() {
        return sysIndex;
    }

    public void setSysIndex(boolean sysIndex) {
        this.sysIndex = sysIndex;
    }
}
