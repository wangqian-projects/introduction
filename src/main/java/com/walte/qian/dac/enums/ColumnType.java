package com.walte.qian.dac.enums;

import com.walte.qian.dac.service.spi.INamedEnum;

/**
 * <p>ClassName: ColumnType</p>
 * <p>Description: 字段类型</p>
 *
 * @author wangqian
 * @date 2018-02-07 11:32
 */
public enum ColumnType implements INamedEnum {
    Normal   (0, "普通字段"),
    Foregin  (1, "外键字段"),
    Reference(2, "引用字段"),
    Virtual  (3, "虚拟字段"),
    Ignore   (4, "忽略字段");

    public static final String baseName = "i18n.enums.ColumnType";

    private final int id;
    private final String title;

    ColumnType(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLocaleTitle() {
        return getTitle();
    }

    @Override
    public String getTitle() {
        return title;
    }
}
