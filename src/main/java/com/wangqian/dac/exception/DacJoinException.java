package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacJoinException</p>
 * <p>Description: SQL连接异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:05
 */
public class DacJoinException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacJoinException(String errMsg) {
        super(errMsg);
    }
}
