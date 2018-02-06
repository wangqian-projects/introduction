package com.walte.qian.dac.exception;
/**
 * <p>ClassName: DacCreateTableException</p>
 * <p>Description: 创建表异常</p>
 *
 * @author wangqian
 * @date 2018-01-24 11:28
 */
public class DacCreateTableException extends DacRuntimeException{

    private static final long serialVersionUID = 1L;

    public DacCreateTableException(String errMsg) {
        super(errMsg);
    }
}
