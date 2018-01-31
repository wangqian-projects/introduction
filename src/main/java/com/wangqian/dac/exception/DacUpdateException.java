package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacUpdateException</p>
 * <p>Description: 更新数据库异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:21
 */
public class DacUpdateException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacUpdateException(String errMsg) {
        super(errMsg);
    }
}
