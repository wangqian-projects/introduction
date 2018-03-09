package com.walte.qian.dac.service.spi.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.walte.qian.dac.enums.ColumnDataEnum;
import com.walte.qian.dac.util.EmptyUtil;

/**
 * <p>ClassName: TableColumn</p>
 * <p>Description: 表结构列</p>
 *
 * @author wangqian
 * @date 2018-03-09 13:39
 */
public class TableColumn {

    private boolean primary; // 是否是主键

    private String columnName; // 列名

    private ColumnDataEnum columnDataEnum; // 字段枚举类型

    private long size; // 长度

    private int decimal; // 精度

    private String bscRemark; // 备注

    private String defaultValue; // 默认值

    private boolean notNull; //是否为空

    private boolean sysField; // 是否是系统字段

    private boolean versionColumn; // 是否是版本字段

    private boolean mutiLanguageColumn; // 是否是多语言字段

    @JsonIgnore
    public String getDefaultValueSql() {
        if (EmptyUtil.isEmpty(defaultValue)) {
            return "";
        }
        switch (getColumnDataEnum()) {
            case BigInt:
            case Float:
            case Numeric:
            case Int:
                return String.format(" default %s ", defaultValue);
            case Blob:
            case Bool:
            case Char:
            case Clob:
            case Date:
            case DateTime:
            case Double:
            case Eunm:
            case Str:
            case Uuid:
                default:
                    return String.format(" default '%s' ", defaultValue);
        }
    }

    public String getColumnDataEnumName() {
        return columnDataEnum == null ? null : columnDataEnum.getLocaleTitle();
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.toUpperCase();
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

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
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

    @Override
    public String toString() {
        return String.format("TableColumn(columnName:%s,columnDataEnum:%s,bscRemark:%s)", columnName, columnDataEnum, bscRemark);
    }
}
