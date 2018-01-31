package com.wangqian.dac.exception;

/**
 * <p>ClassName: DacUpdateVersionException</p>
 * <p>Description: 更新数据看异常, 版本错误</p>
 * 
 * @author wangqian
 * @date 2018-01-31 11:24
 */
public class DacUpdateVersionException extends DacUpdateException {

    private static final long serialVersionUID = 1L;

    public DacUpdateVersionException(String errMsg) {
        super(errMsg);
    }
}
