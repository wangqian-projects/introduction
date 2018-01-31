package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacNotNullException</p>
 * <p>Description: 不可为空异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:10
 */
public class DacNotNullException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacNotNullException(String errMsg) {
        super(errMsg);
    }
}
