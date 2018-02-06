package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacDataNotUniqueException</p>
 * <p>Description: 数据不唯一异常</p>
 * 
 * @author wangqian
 * @date 2018-01-24 11:55
 */
public class DacDataNotUniqueException extends DacRuntimeException{

    private static final long serialVersionUID = 1L;

    public DacDataNotUniqueException(String errMsg) {
        super(errMsg);
    }
}
