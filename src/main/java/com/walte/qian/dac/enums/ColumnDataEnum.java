package com.walte.qian.dac.enums;

import com.walte.qian.dac.service.spi.INamedEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.Date;

/**
 * <p>ClassName: ColumnDataEnum</p>
 * <p>Description: 数据库字段类型</p>
 *
 * @author wangqian
 * @date 2018-02-07 11:00
 */
public enum ColumnDataEnum implements INamedEnum {
    Bool    (0, "布尔",   Boolean.class),
    Int     (1, "整型",   Integer.class),
    BigInt  (2, "长整型", Long.class),

    Float   (3, "浮点数", Float.class),
    Double  (4, "双精度", Double.class),
    Numeric (5, "定点数", BigDecimal.class),

    Char    (6, "单字符", Character.class),
    Str     (7, "字符串", String.class),
    Eunm    (8, "枚举",   void.class),
    Uuid    (9, "UUID",   UUID.class),

    Blob    (10, "大对象", Byte.class),
    Clob    (11, "打文本", String.class),
    Date    (12, "日期",   Date.class),
    DateTime(13, "日期时间", Timestamp.class);


    public static String baseName = "i18n.enums.ColumnDataEnum";

    private final int id;
    private final String title;
    private final Class<?> javaClass;

    ColumnDataEnum(int id, String title, Class<?> javaClass) {
        this.id = id;
        this.title = title;
        this.javaClass = javaClass;
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

    public Class<?> getJavaClass() {
        return javaClass;
    }
}
