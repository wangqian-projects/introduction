package com.walte.qian.notes.design.strategy.java;

/**
 * 赵云
 *
 * @author 王骞
 * @date 2019-10-24
 */
public class ZhaoYun {

    /**
     * 接口锦囊妙计
     */
    private JinNangInterfce jinNang;

    public ZhaoYun(JinNangInterfce jinNang) {
        this.jinNang = jinNang;
    }

    /**
     * 打开锦囊
     */
    public void open() {
        this.jinNang.use();
    }
}
