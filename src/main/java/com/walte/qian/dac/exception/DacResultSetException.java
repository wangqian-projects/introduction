package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacResultSetException</p>
 * <p>Description: 查询数据库结果集异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:16
 */
public class DacResultSetException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacResultSetException(String errMsg) {
        super(errMsg);
    }
}
