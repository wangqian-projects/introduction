package com.walte.qian.dac.service.spi;

/**
 * <p>ClassName: INamedEnum</p>
 * <p>Description: 命名枚举</p>
 *
 * @author wangqian
 * @date 2018-02-07 11:06
 */
public interface INamedEnum {

    /**
     * 枚举值标识
     *
     * @return
     */
    public int getId();

    /**
     * 获取多语言信息,没有多语言信息直接返回title即可
     *
     * @return
     */
    public String getLocaleTitle();


    /**
     * 枚举值标题
     *
     * @return
     */
    public String getTitle();

}
