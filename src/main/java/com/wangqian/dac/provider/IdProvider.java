package com.wangqian.dac.provider;

import com.wangqian.dac.enums.SingleInstanceGenerator;

/**
 * <p>ClassName: SnowflakeIdProvider</p>
 * <p>Description: 雪花算法ID</p>
 *
 * @author wangqian
 * @date 2018-01-19 16:29
 */
public class IdProvider {


    /**
     * 获取下一个ID
     *
     * @return id
     */
    public static long nextId() {
        return SingleInstanceGenerator.SNOWFLAKE_ID_WORKER.getSnowflakeIdWorker().nextId();
    }


}
