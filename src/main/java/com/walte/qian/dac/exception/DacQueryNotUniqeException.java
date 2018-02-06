package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacQueryNotUniqeException</p>
 * <p>Description: 查询结果不唯一异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:15
 */
public class DacQueryNotUniqeException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacQueryNotUniqeException(String errMsg) {
        super(errMsg);
    }
}
