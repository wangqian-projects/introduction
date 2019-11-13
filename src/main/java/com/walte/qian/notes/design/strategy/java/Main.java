package com.walte.qian.notes.design.strategy.java;

/**
 * 执行
 *
 * @author 王骞
 * @date 2019-10-24
 */
public class Main {

    public static void main(String[] args) {
        ZhaoYun zhaoYun = null;
        // 1.刚到吴国打开第一个
        System.out.println("---刚到吴国打开第一个---");
        zhaoYun = new ZhaoYun(new JinNangA());
        zhaoYun.open();

        // 2.刘备乐不思蜀打开第二个
        System.out.println("---刘备乐不思蜀打开第二个---");
        zhaoYun = new ZhaoYun(new JinNangB());
        zhaoYun.open();

        // 3.孙权追兵打开第三个
        System.out.println("---孙权追兵打开第三个---");
        zhaoYun = new ZhaoYun(new JinNangC());
        zhaoYun.open();
    }

}
