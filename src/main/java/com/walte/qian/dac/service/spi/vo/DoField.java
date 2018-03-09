package com.walte.qian.dac.service.spi.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.walte.qian.dac.enums.ColumnType;
import com.walte.qian.dac.enums.FkType;
import com.walte.qian.dac.ident.BizIdentUtils;
import com.walte.qian.dac.util.EmptyUtil;

import java.util.UUID;

public class DoField extends TableColumn {

    private long bscId; // 主键ID

    private String bscName; // 名称

    private String title; //模型展示标题

    private ColumnType columnType; //字段类型

    private String bizIdent; // 业务标识


    /****************** 引用字段 *********************/

    private FkType refType; // 引用类型

    private String relyPath; // 关联路径

    private String relyPathName; //

    private String refField; // 引用字段

    private String refFieldName; //

    private boolean redundantStorage; //如果强关联, 是否是冗余存储


    /****************** 外键字段信息 *********************/

    private boolean dynamicFk; // 是否是动态外键

    private FkType fkType; //外键类型

    private String fkGroup; // 相同的fkGroup则为一组关联字段

    private UUID fkTableUuid; // 数据源, 关联表的uuid

    private String fkTableName; // 关联表的名称

    private String fkTargetColumn; // 关联字段

    private String fkTargetColumnName;

    private boolean mustJoin; // 外键是否强关联


    /****************** 页面UI信息 *********************/

    private UUID memberScope; // 成员范围

    private boolean readonly; //是否只读

    private boolean hidden; //是否隐藏

    private boolean selectLeaf; //是否只选叶子节点

    private boolean maintain; // 页面是否维护

    private Class<?> enumClass;


    public long getBscId() {
        return bscId;
    }

    public void setBscId(long bscId) {
        this.bscId = bscId;
    }

    public String getBscName() {
        return EmptyUtil.isEmpty(bscName) ? super.getColumnName(): bscName;
    }

    public void setBscName(String bscName) {
        this.bscName = bscName;
    }

    public String getTitle() {
        return EmptyUtil.isEmpty(title) ? getBscName() : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public String getColumnTypeName() {
        return columnType == null ? null : columnType.getLocaleTitle();
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getBizIdent() {
        return bizIdent;
    }

    public String getBizIdentName() {
        return BizIdentUtils.getIdentTitle(bizIdent);
    }

    public void setBizIdent(String bizIdent) {
        this.bizIdent = bizIdent;
    }

    public FkType getRefType() {
        return refType;
    }

    public String getRefTypeName() {
        return refType == null ? null : refType.getLocaleTitle();
    }

    public void setRefType(FkType refType) {
        this.refType = refType;
    }

    public String getRelyPath() {
        return relyPath;
    }

    public void setRelyPath(String relyPath) {
        this.relyPath = relyPath;
    }

    public String getRelyPathName() {
        return relyPathName;
    }

    public void setRelyPathName(String relyPathName) {
        this.relyPathName = relyPathName;
    }

    public String getRefField() {
        return refField;
    }

    public void setRefField(String refField) {
        this.refField = refField;
    }

    public String getRefFieldName() {
        return refFieldName;
    }

    public void setRefFieldName(String refFieldName) {
        this.refFieldName = refFieldName;
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

    public String getFkTypeName() {
        return fkType == null ? null : fkType.getLocaleTitle();
    }

    public void setFkType(FkType fkType) {
        this.fkType = fkType;
    }

    public String getFkGroup() {
        return fkGroup;
    }

    public void setFkGroup(String fkGroup) {
        this.fkGroup = fkGroup;
    }

    public UUID getFkTableUuid() {
        return fkTableUuid;
    }

    public void setFkTableUuid(UUID fkTableUuid) {
        this.fkTableUuid = fkTableUuid;
    }

    public String getFkTableName() {
        return fkTableName;
    }

    public void setFkTableName(String fkTableName) {
        this.fkTableName = fkTableName;
    }

    public String getFkTargetColumn() {
        return fkTargetColumn;
    }

    public void setFkTargetColumn(String fkTargetColumn) {
        this.fkTargetColumn = fkTargetColumn;
    }

    public String getFkTargetColumnName() {
        return fkTargetColumnName;
    }

    public void setFkTargetColumnName(String fkTargetColumnName) {
        this.fkTargetColumnName = fkTargetColumnName;
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

    public boolean isSelectLeaf() {
        return selectLeaf;
    }

    public void setSelectLeaf(boolean selectLeaf) {
        this.selectLeaf = selectLeaf;
    }

    public boolean isMaintain() {
        return maintain;
    }

    public void setMaintain(boolean maintain) {
        this.maintain = maintain;
    }

    public Class<?> getEnumClass() {
        return enumClass;
    }

    public void setEnumClass(Class<?> enumClass) {
        this.enumClass = enumClass;
    }

    /**
     * 是否是页面操作字段
     * @return boolean
     */
    @JsonIgnore
    public boolean isNotOptionColum() {
        return isHidden() || isPrimary() || getColumnType() == ColumnType.Virtual
                || getColumnType() == ColumnType.Reference || getColumnType() == ColumnType.Ignore;
    }

    @Override
    public String toString() {
        return String.format("DoField(bscName:%s:columnType:%s,bizIdent:%s)", bscName, columnType, bizIdent);
    }
}
