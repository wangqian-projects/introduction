package com.walte.qian.dac.exception;

/**
 * <p>ClassName: DacNewInstanceException</p>
 * <p>Description: 创建实例异常</p>
 *
 * @author wangqian
 * @date 2018-01-31 11:08
 */
public class DacNewInstanceException extends DacRuntimeException {

    private static final long serialVersionUID = 1L;

    public DacNewInstanceException(Class<?> clazz) {
        super(String.format("%s初始化失败，请检查是否缺少public缺省构造函数",clazz.getName()));
    }
}
