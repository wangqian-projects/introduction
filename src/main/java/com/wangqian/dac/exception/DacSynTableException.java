package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacSynTableException</p>
 * <p>Description: 同步表结构异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:17
 */
public class DacSynTableException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacSynTableException(String errMsg) {
        super(errMsg);
    }
}
