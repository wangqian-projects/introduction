package com.walte.qian.dac.enums;

import com.walte.qian.dac.util.CollectionsUtil;
import com.walte.qian.dac.util.EmptyUtil;

import java.util.Map;

/**
 * <p>ClassName: DataBaseType</p>
 * <p>Description: 数据库类型</p>
 *
 * @author wangqian
 * @date 2018-02-07 11:41
 */
public enum DataBaseType {

    /**
     * Oracle数据库
     */
    ORACLE(0, "ORACLE"),

    /**
     * sql server数据库
     */
    SQLSERVER(1, "SQLSERVER"),

    /**
     * mysql数据库
     */
    MYSQL(2, "MYSQL");

    private static Map<Integer, DataBaseType> finder;

    static {
        finder = CollectionsUtil.newHashMap();
        for (DataBaseType item : DataBaseType.values()) {
            finder.put(item.id, item);
        }
    }

    public static DataBaseType parse(String str) {
        if (EmptyUtil.isEmpty(str)) {
            return null;
        }

        try {
            int val = Integer.parseInt(str);
            DataBaseType ret = finder.get(val);
            if (ret != null) {
                return ret;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        for (DataBaseType item : values()) {
            if (item.getTitle().equalsIgnoreCase(str)) {
                return item;
            }
        }

        return null;
    }


    private final int id;
    private final String title;

    DataBaseType(int id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * 枚举值标识
     *
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     * 枚举值标题
     *
     * @return
     */
    public String getTitle() {
        return title;
    }
}
