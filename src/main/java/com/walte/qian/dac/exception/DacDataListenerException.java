package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacDataListenerException</p>
 * <p>Description: 数据监听异常</p>
 *
 * @author wangqian
 * @date 2018-01-24 11:52
 */
public class DacDataListenerException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacDataListenerException(String errMsg, int i) {
        super(errMsg);

    }
}
