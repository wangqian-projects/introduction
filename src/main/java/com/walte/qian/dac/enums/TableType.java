package com.walte.qian.dac.enums;

import com.walte.qian.dac.service.spi.INamedEnum;

/**
 * <p>ClassName: TableType</p>
 * <p>Description: 表类型</p>
 * 
 * @author wangqian
 * @date 2018-02-07 15:37
 */
public enum TableType implements INamedEnum{
    SYSTEM   (0, "系统表"),
    BUSINESS (1, "业务表"),
    BOTH     (2, "两者");

    private final int id;
    private final String title;

    TableType(int id, String title) {
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
