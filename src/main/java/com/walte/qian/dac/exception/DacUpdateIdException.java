package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacUpdateIdException</p>
 * <p>Description: 更新数据库异常, ID不存在</p>
 * 
 * @author wangqian
 * @date 2018-01-31 11:22
 */
public class DacUpdateIdException extends DacUpdateException {

    private static final long serialVersionUID = 1L;

    public DacUpdateIdException(String errMsg) {
        super(errMsg);
    }
}
