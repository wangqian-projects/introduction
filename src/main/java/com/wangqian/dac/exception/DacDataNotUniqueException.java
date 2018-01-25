package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacDataNotUniqueException</p>
 * <p>Description: </p>
 * 
 * @author wangqian
 * @date 2018-01-24 11:55
 */
public class DacDataNotUniqueException extends DacRuntimeException{

    private static final long serialVersionUID = -1;

    public DacDataNotUniqueException(String errMsg) {
        super(errMsg);
    }
}
