package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacInsertException</p>
 * <p>Description: 插入异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:03
 */
public class DacInsertException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacInsertException(String errMsg) {
        super(errMsg);
    }
}
