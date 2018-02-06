package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacLockException</p>
 * <p>Description: 数据库加锁异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:06
 */
public class DacLockException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacLockException(String errMsg) {
        super(errMsg);
    }
}
