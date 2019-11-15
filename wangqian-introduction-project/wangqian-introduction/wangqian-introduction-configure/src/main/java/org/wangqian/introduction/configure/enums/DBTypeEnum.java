package org.wangqian.introduction.configure.enums;

/**
 * @author 王骞
 * @date 2019-11-05
 */
public enum DBTypeEnum {

    DB_P("dbp", "lsj_p"),
    DB_BASE("dbbase", "lsj_base"),
    DB_PO("dbpo", "lsj_po");
    private String value;
    private String title;

    DBTypeEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public static DBTypeEnum getDefault() {
        return DB_P;
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
