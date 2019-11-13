package com.walte.qian.notes.design.strategy.java;

/**
 * 策略枚举 上下文角色
 *
 * @author 王骞
 * @date 2019-10-24
 */
public enum ZhaoYunEnum {

    /**
     * 具体策略角色_A
     */
    JIN_NANG_A(new JinNangInterfce() {
        @Override
        public void use() {
            System.out.println("见乔国老，并把刘备娶亲的事情搞得东吴人尽皆知！");
        }
    }),
    /**
     * 具体策略角色_B
     */
    JIN_NANG_B(new JinNangInterfce() {
        @Override
        public void use() {
            System.out.println("用谎言（曹操打荆州）骗泡在温柔乡里的刘备回去！");
        }
    }),
    /**
     * 具体策略角色_C
     */
    JIN_NANG_C(new JinNangInterfce() {
        @Override
        public void use() {
            System.out.println("让孙夫人摆平东吴的追兵，她是孙权妹妹，东吴将领惧她三分！");
        }
    }),
    JIN_NANG_D(new JinNangInterfce() {
        @Override
        public void use() {
            System.out.println("让孙夫人摆平东吴的追兵，她是孙权妹妹，东吴将领惧她三分！");
        }
    })
    ;

    /**
     * 抽象策略属性
     */
    private JinNangInterfce jinNang;

    ZhaoYunEnum(JinNangInterfce jinNang) {
        this.jinNang = jinNang;
    }

    /**
     * 打开锦囊
     */
    public void open() {
        this.jinNang.use();
    }

    public static void main(String[] args) {

    }

}
