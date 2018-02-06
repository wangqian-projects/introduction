package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacExecDDLException</p>
 * <p>Description: 执行DDL异常</p>
 *
 * @author wangqian
 * @date 2018-01-24 11:58
 */
public class DacExecDDLException extends DacRuntimeException{

    private static final long serialVersionUID = 1L;

    public DacExecDDLException(String errMsg) {
        super(errMsg);
    }
}
