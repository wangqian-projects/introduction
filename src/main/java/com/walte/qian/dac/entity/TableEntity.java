package com.walte.qian.dac.entity;

import com.walte.qian.dac.annotation.DBColumn;
import com.walte.qian.dac.annotation.DBEntity;

import java.util.Date;
import java.util.UUID;

/**
 * <p>ClassName: TableEntity</p>
 * <p>Description: 数据库表元数据存储表</p>
 * 
 * @author wangqian
 * @date 2018-02-24 17:17
 */
@DBEntity(tableName = "sys_Table", uuid = "e061d5ae-d178-46f3-8d05-2e2ff645544b", finalEntity = true, version = "1.0")
public class TableEntity {

    @DBColumn(comment = "主键", primaryKey = true)
    private UUID uuid;

    @DBColumn(comment = "表名称")
    private String bscName;

    @DBColumn(comment = "表名")
    private String tableName;

    @DBColumn(comment = "表注释")
    private String bscRemark;

    @DBColumn(comment = "表类型")
    private boolean sysTable;

    @DBColumn(comment = "class类上记录的版本号")
    private String classVer;

    @DBColumn(comment = "class类上记录的重构表的版本号")
    private String rebuildVer;

    @DBColumn(comment = "表模板UUID")
    private UUID templateUuid;

    @DBColumn(comment = "页面是否可见")
    private boolean visable;

    @DBColumn(comment = "列配需", size = 1000)
    private String fieldOrder;

    @DBColumn(comment = "修改时间")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime = new Date();

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getBscName() {
        return bscName;
    }

    public void setBscName(String bscName) {
        this.bscName = bscName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBscRemark() {
        return bscRemark;
    }

    public void setBscRemark(String bscRemark) {
        this.bscRemark = bscRemark;
    }

    public boolean isSysTable() {
        return sysTable;
    }

    public void setSysTable(boolean sysTable) {
        this.sysTable = sysTable;
    }

    public String getClassVer() {
        return classVer;
    }

    public void setClassVer(String classVer) {
        this.classVer = classVer;
    }

    public String getRebuildVer() {
        return rebuildVer;
    }

    public void setRebuildVer(String rebuildVer) {
        this.rebuildVer = rebuildVer;
    }

    public UUID getTemplateUuid() {
        return templateUuid;
    }

    public void setTemplateUuid(UUID templateUuid) {
        this.templateUuid = templateUuid;
    }

    public boolean isVisable() {
        return visable;
    }

    public void setVisable(boolean visable) {
        this.visable = visable;
    }

    public String getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(String fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
