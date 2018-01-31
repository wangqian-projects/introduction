package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacUnsupportException</p>
 * <p>Description: 不支持异常</p>
 * 
 * @author wangqian
 * @date 2018-01-31 11:20
 */
public class DacUnsupportException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacUnsupportException(String errMsg) {
        super(errMsg);
    }
}
