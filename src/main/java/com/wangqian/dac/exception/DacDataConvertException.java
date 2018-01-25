package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacDataConvertException</p>
 * <p>Description: 数据转换异常</p>
 *
 * @author wangqian
 * @date 2018-01-24 11:29
 */
public class DacDataConvertException extends DacRuntimeException{

    public DacDataConvertException(String errMsg) {
        super(errMsg);
    }
}
