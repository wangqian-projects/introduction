package com.walte.qian.dac.dataSet;

import com.walte.qian.dac.service.spi.vo.DoField;

/**
 * 数据集数据转化Map特殊回调接口
 */
public interface ToMapCall {

    public enum CallType {
        Ignore,
        Empty,
        Value;
    }

    /**
     * 获取转化后的值
     * @return Object
     */
    public Object getValue();

    /**
     * 对应列转化
     * @param df DoField
     * @param value Object
     * @return CallType
     */
    public CallType toMap(DoField df, Object value);


}
