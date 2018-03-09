package com.walte.qian.dac.ident;

import com.walte.qian.dac.enums.ColumnDataEnum;
import com.walte.qian.dac.util.CollectionsUtil;

import java.util.Set;

public class BizIdentVo implements Comparable<BizIdentVo> {

    private final String name;

    private final Float order;

    private final boolean dynamicFK;

    private Set<ColumnDataEnum> columnDataEnumSet = CollectionsUtil.newHashSet();

    public BizIdentVo(String name, Float order, boolean dynamicFK, ColumnDataEnum... columnDataEnums) {
        this.name = name;
        this.order = order;
        this.dynamicFK = dynamicFK;
        if (columnDataEnums != null) {
            for (ColumnDataEnum columnDataEnum : columnDataEnums) {
                columnDataEnumSet.add(columnDataEnum);
            }
        }
    }

    @Override
    public int compareTo(BizIdentVo o) {
        return order.compareTo(o.order);
    }

    /**
     * 获取名称
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * 获取业务标识标题
     *
     * @return String
     */
    public String getTitle() {
        return BizIdentUtils.getIdentTitle(name);
    }

    public boolean isDynamicFK() {
        return dynamicFK;
    }

    /**
     * 是否支持该数据类型
     *
     * @param columnDataEnum 数据库字段类型
     * @return boolean
     */
    public boolean isSupport(ColumnDataEnum columnDataEnum) {
        if (null == columnDataEnum) {
            return false;
        }
        return columnDataEnumSet.contains(columnDataEnum);
    }


}
