package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacParamException</p>
 * <p>Description: 参数异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:13
 */
public class DacParamException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacParamException(String errMsg) {
        super(errMsg);
    }
}
