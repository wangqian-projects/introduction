package com.walte.qian.utils.sys;

import org.junit.Test;
import java.util.UUID;

/**
 * SysUtil单元测试
 */
public class SysUtilTest {

    /**
     * UUID测试
     */
//    @Test
    public void UUIDUtilTest() {
//        UUID randomUUID = UUIDUtil.getRandomUUID();
//        System.out.println("这是随机生成的uuid: " +randomUUID.toString());
//
//        String[] fragments = UUIDUtil.splitUUIDWithHyphen(UUIDUtil.getRandomUUID());
//
//        for (int i = 0; i < fragments.length; i++) {
//            System.out.println("第" + i + "个片段：" + fragments[i]);
//        }

        String uuid = UUIDUtil.getUUID(12);
        System.out.println(uuid + " =12=  {" + uuid.length());
        String uuid1 = UUIDUtil.getUUID(445);
        System.out.println(uuid1 + "=32=  {" + uuid1.length());

    }

    /**
     *数字金额大写转换
     */
    @Test
    public void MoneyUtilTest() {
        String s1 = MoneyUtil.digitUppercase(2352345.39);
        String s2 = MoneyUtil.digitUppercase(112561564234345.00);
        String s3 = MoneyUtil.digitUppercase(2352345);
        String s4 = MoneyUtil.digitUppercase(-798525.355);
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("s3: " + s3);
        System.out.println("s4: " + s4);

        //s1: 贰佰叁拾伍万贰仟叁佰肆拾伍元叁角玖分
        //s2: 壹佰壹拾贰兆伍仟陆佰壹拾伍亿陆仟肆佰贰拾叁万肆仟叁佰肆拾伍元整
        //s3: 贰佰叁拾伍万贰仟叁佰肆拾伍元整
        //s4: 负柒拾玖万捌仟伍佰贰拾伍元叁角伍分
    }
}
