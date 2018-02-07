package com.walte.qian.dac.enums;

import com.walte.qian.dac.service.spi.INamedEnum;

public enum FkType implements INamedEnum {
    TableFK (0, "表外键"),
    TableFK2(1, "表外键2(多成员)"),
    UnionFK (2, "复合外键"),
    DimFK   (3, "维度外键"),
    DimFK2  (4, "维度外键2(多成员)");

    private final int id;
    private final String title;

    FkType(int id, String title) {
        this.id = id;
        this.title = title;
    }


    /**
     * 枚举值标识
     *
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * 获取多语言信息,没有多语言信息直接返回title即可
     *
     * @return
     */
    @Override
    public String getLocaleTitle() {
        return getTitle();
    }

    /**
     * 枚举值标题
     *
     * @return
     */
    @Override
    public String getTitle() {
        return title;
    }
}
