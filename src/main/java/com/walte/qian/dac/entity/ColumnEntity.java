package com.walte.qian.dac.entity;

import com.walte.qian.dac.annotation.DBColumn;
import com.walte.qian.dac.annotation.DBEntity;
import com.walte.qian.dac.annotation.DBRelationField;
import com.walte.qian.dac.enums.ColumnDataEnum;
import com.walte.qian.dac.enums.ColumnType;
import com.walte.qian.dac.enums.FkType;

import java.util.Date;
import java.util.UUID;

/**
 * <p>ClassName: ColumnEntity</p>
 * <p>Description: 数据库列元数据存储表</p>
 *
 * @author wangqian
 * @date 2018-03-08 17:15
 */
@DBEntity(tableName = "sys_Column", uuid = "180bc436-220a-4e7c-ae32-3446bb1b642e", finalEntity = true, version = "1.0")
public class ColumnEntity {

    /**************************
     * 数据库的基本信息
     ***************************/
    @DBColumn(primaryKey = true, comment = "主键")
    private long bscId;

    @DBColumn(comment = "模型的UUID")
    private UUID tableUuid;

    @DBColumn(comment = "表字段列名, 数据模型字段的编码存储此字段")
    private String columnName;

    @DBColumn(comment = "数据类型")
    private ColumnDataEnum columnDataEnum;

    @DBColumn(comment = "长度")
    private long size;

    @DBColumn(comment = "小数位数")
    private int decimal;

    @DBColumn(comment = "备注", size = 500)
    private String bscRemark;

    @DBColumn(comment = "默认值")
    private String defaultValue;

    @DBColumn(comment = "是否是主键")
    private boolean primary;

    @DBColumn(comment = "不能为空")
    private boolean notNull;

    @DBColumn(comment = "修改时间")
    private Date modifyTime = new Date();


    /********************************
     * 系统扩展出的数据库列属性
     *******************************/
    @DBColumn(comment = "根据Class生成的系统字段")
    private boolean sysField;

    @DBColumn(comment = "是否是版本控制字段")
    private boolean versionColumn;

    @DBColumn(comment = "是否是多语言字段")
    private boolean mutiLanguageColumn;


    /********************************
     * 业务信息
     *******************************/
    @DBColumn(comment = "列字段名称")
    private String bscName;

    @DBColumn(comment = "模型展示标题")
    private String title;

    @DBColumn(comment = "业务标识")
    private String bizIdent;

    @DBColumn(comment = "列字段类型")
    private ColumnType columnType;


    /********************************
     * 引用字段信息
     *******************************/
    @DBColumn(comment = "依赖路径", size = 300)
    private String relyPath;

    @DBColumn(comment = "关联字段")
    private String refField;

    @DBColumn(comment = "强关联, 是否冗余存储")
    private boolean redundantStorage;


    /********************************
     * 外键字段信息
     *******************************/
    @DBColumn(comment = "是否是动态外键")
    private boolean dynamicFk;

    @DBColumn(comment = "外键类型")
    private FkType fkType;

    @DBColumn(comment = "数据源, 外键字段关联表的UUID")
    @DBRelationField(refField = "bscName", relyPath = "fkTableUuid")
    private UUID fkTableUuid;

    @DBColumn(comment = "相同的fkGroup则为一组关联字段")
    private String fkGroup;

    @DBColumn(comment = "外键关联字段")
    private String fkTargetColumn;

    @DBColumn(comment = "强关联")
    private boolean mustJoin;


    /********************************
     * 页面UI信息
     *******************************/
    @DBColumn(comment = "成员范围")
    private UUID memberScope;

    @DBColumn(comment = "只能选择叶子")
    private boolean selectLeaf;

    @DBColumn(comment = "是否只读")
    private boolean readonly;

    @DBColumn(comment = "是否隐藏")
    private boolean hidden;

    @DBColumn(comment = "页面是否维护")
    private boolean maintain;


    public long getBscId() {
        return bscId;
    }

    public void setBscId(long bscId) {
        this.bscId = bscId;
    }

    public UUID getTableUuid() {
        return tableUuid;
    }

    public void setTableUuid(UUID tableUuid) {
        this.tableUuid = tableUuid;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ColumnDataEnum getColumnDataEnum() {
        return columnDataEnum;
    }

    public void setColumnDataEnum(ColumnDataEnum columnDataEnum) {
        this.columnDataEnum = columnDataEnum;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDecimal() {
        return decimal;
    }

    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }

    public String getBscRemark() {
        return bscRemark;
    }

    public void setBscRemark(String bscRemark) {
        this.bscRemark = bscRemark;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean isSysField() {
        return sysField;
    }

    public void setSysField(boolean sysField) {
        this.sysField = sysField;
    }

    public boolean isVersionColumn() {
        return versionColumn;
    }

    public void setVersionColumn(boolean versionColumn) {
        this.versionColumn = versionColumn;
    }

    public boolean isMutiLanguageColumn() {
        return mutiLanguageColumn;
    }

    public void setMutiLanguageColumn(boolean mutiLanguageColumn) {
        this.mutiLanguageColumn = mutiLanguageColumn;
    }

    public String getBscName() {
        return bscName;
    }

    public void setBscName(String bscName) {
        this.bscName = bscName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBizIdent() {
        return bizIdent;
    }

    public void setBizIdent(String bizIdent) {
        this.bizIdent = bizIdent;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getRelyPath() {
        return relyPath;
    }

    public void setRelyPath(String relyPath) {
        this.relyPath = relyPath;
    }

    public String getRefField() {
        return refField;
    }

    public void setRefField(String refField) {
        this.refField = refField;
    }

    public boolean isRedundantStorage() {
        return redundantStorage;
    }

    public void setRedundantStorage(boolean redundantStorage) {
        this.redundantStorage = redundantStorage;
    }

    public boolean isDynamicFk() {
        return dynamicFk;
    }

    public void setDynamicFk(boolean dynamicFk) {
        this.dynamicFk = dynamicFk;
    }

    public FkType getFkType() {
        return fkType;
    }

    public void setFkType(FkType fkType) {
        this.fkType = fkType;
    }

    public UUID getFkTableUuid() {
        return fkTableUuid;
    }

    public void setFkTableUuid(UUID fkTableUuid) {
        this.fkTableUuid = fkTableUuid;
    }

    public String getFkGroup() {
        return fkGroup;
    }

    public void setFkGroup(String fkGroup) {
        this.fkGroup = fkGroup;
    }

    public String getFkTargetColumn() {
        return fkTargetColumn;
    }

    public void setFkTargetColumn(String fkTargetColumn) {
        this.fkTargetColumn = fkTargetColumn;
    }

    public boolean isMustJoin() {
        return mustJoin;
    }

    public void setMustJoin(boolean mustJoin) {
        this.mustJoin = mustJoin;
    }

    public UUID getMemberScope() {
        return memberScope;
    }

    public void setMemberScope(UUID memberScope) {
        this.memberScope = memberScope;
    }

    public boolean isSelectLeaf() {
        return selectLeaf;
    }

    public void setSelectLeaf(boolean selectLeaf) {
        this.selectLeaf = selectLeaf;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isMaintain() {
        return maintain;
    }

    public void setMaintain(boolean maintain) {
        this.maintain = maintain;
    }
}
