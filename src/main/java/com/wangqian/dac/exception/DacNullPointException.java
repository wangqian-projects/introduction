package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacNullPointException</p>
 * <p>Description: 空指针异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:12
 */
public class DacNullPointException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacNullPointException(String errMsg) {
        super(errMsg);
    }
}
