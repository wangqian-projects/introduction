package org.wangqian.introduction.configure.enums;

/**
 * @author 王骞
 * @date 2019-11-05
 */
public enum DBTypeEnum {

    /**
     * 数据源 1
     */
    DATA_SOURCE1("datasource1", "datasource1"),
    /**
     * 数据源 2
     */
    DATA_SOURCE2("datasource2", "datasource2"),
    /**
     * 数据源 3
     */
    DATA_SOURCE3("datasource3", "datasource3");

    private String value;
    private String title;

    DBTypeEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public static DBTypeEnum getDefault() {
        return DATA_SOURCE1;
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
