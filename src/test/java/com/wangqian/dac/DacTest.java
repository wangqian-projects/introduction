package com.wangqian.dac;

import com.wangqian.dac.provider.IdProvider;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>ClassName: DacTest</p>
 * <p>Description: </p>
 *
 * @author wangqian
 * @date 2018-01-19 17:45
 */
public class DacTest {

    @Test
    public void IdProviderTest() {

        Set<Long> set = new HashSet<Long>();
//        int limit = 1000;
//        for (int i = 0; i < limit; i++) {
//            set.add(IdProvider.nextId());
//        }
//        System.out.println(String.format("产生的ID总数为 %s, 如果小于 %S, 则说明ID有重复", set.size(), limit));

        long start = System.currentTimeMillis();
        for (int j = 0; j < 26*100000; j++) {
            IdProvider.nextId();
        }
        System.out.println(String.format("时间消耗为: %s",System.currentTimeMillis() - start));
        //测试结果: 获取260万ID需要638毫秒
//        System.out.println(set.size());
//        for (Long ll : set) {
//            System.out.println(ll);
//        }


    }
}
