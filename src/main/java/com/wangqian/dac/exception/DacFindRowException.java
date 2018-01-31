package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacFindRowException</p>
 * <p>Description: 查找行异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:00
 */
public class DacFindRowException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacFindRowException(String errMsg) {
        super(errMsg);
    }
}
