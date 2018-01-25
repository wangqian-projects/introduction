package com.wangqian.dac.exception;

import java.util.HashMap;

/**
 * <p>ClassName: DacRuntimeException</p>
 * <p>Description: 异常</p>
 * 
 * @author wangqian
 * @date 2018-01-24 11:22
 */
public abstract class DacRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -1;

    public DacRuntimeException(String errMsg) {
        super(errMsg);
        HashMap hashMap = new HashMap();
    }

}
