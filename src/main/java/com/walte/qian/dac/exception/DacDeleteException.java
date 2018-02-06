package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacDeleteException</p>
 * <p>Description: 删除异常</p>
 * 
 * @author wangqian
 * @date 2018-01-24 11:57
 */
public class DacDeleteException extends DacRuntimeException{

    private static final long serialVersionUID = 1L;

    public DacDeleteException(String errMsg) {
        super(errMsg);
    }
}
