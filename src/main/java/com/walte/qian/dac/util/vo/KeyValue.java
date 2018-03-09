package com.walte.qian.dac.util.vo;

/**
 * <p>ClassName: KeyValue</p>
 * <p>Description: 键值对</p>
 * 
 * @author wangqian
 * @date 2018-03-09 17:07
 */
public class KeyValue {

    private final String key;

    private final Object value;


    public KeyValue(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
