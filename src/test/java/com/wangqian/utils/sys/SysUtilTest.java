package com.wangqian.utils.sys;

import org.junit.Test;

import java.util.UUID;

/**
 * SysUtil单元测试
 */
public class SysUtilTest {

    /**
     * UUID测试
     */
    @Test
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
}
