package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacQueryException</p>
 * <p>Description: 数据库查询异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:14
 */
public class DacQueryException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacQueryException(String errMsg) {
        super(errMsg);
    }
}
