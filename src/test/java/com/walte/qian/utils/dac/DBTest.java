package com.walte.qian.utils.dac;

import com.walte.qian.dac.util.SnowflakeIdWorker;

import java.util.HashSet;
import java.util.Set;

/**
 * DB类工具测试
 */
public class DBTest {

//    @Test
    public void IdWorkerTest() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(12,13);
        Set<Long> set = new HashSet<Long>();
        int limit = 1000;
        for (int i = 0; i < limit; i++) {
            set.add(idWorker.nextId());
        }
        System.out.println(String.format("产生的ID总数为 %s, 如果小于 %S, 则说明ID有重复", set.size(), limit));

        long start = System.currentTimeMillis();
        for (int j = 0; j < 26*10000; j++) {
            idWorker.nextId();
        }
        System.out.println(String.format("时间消耗为: %s",System.currentTimeMillis() - start));
        //测试结果: 获取26万ID需要0.78秒
    }
}
