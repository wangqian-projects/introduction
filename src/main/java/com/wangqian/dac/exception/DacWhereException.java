package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacWhereException</p>
 * <p>Description: WHERE条件异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:25
 */
public class DacWhereException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacWhereException(String errMsg) {
        super(errMsg);
    }
}
