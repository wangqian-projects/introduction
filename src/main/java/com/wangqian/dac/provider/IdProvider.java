package com.wangqian.dac.provider;

import com.wangqian.dac.enums.SingleInstanceGenerator;
import com.wangqian.utils.dac.SnowflakeIdWorker;
import config.ConfigExact;

/**
 * <p>ClassName: SnowflakeIdProvider</p>
 * <p>Description: 自增ID提供者</p>
 *
 * @author wangqian
 * @date 2018-01-19 16:29
 */
public class IdProvider implements Provider{

    private final static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(ConfigExact.getWorkerId(), ConfigExact.getDatacenterId());

    /**
     * 获取下一个ID
     *
     * @return id
     */
    public static long nextId() {
        return ((SnowflakeIdWorker)SingleInstanceGenerator.SNOWFLAKE_ID_WORKER_A.getSingleInstance()).nextId();
//        return ((SnowflakeIdWorker)SingleInstanceGenerator.SNOWFLAKE_ID_WORKER_B.getSingleInstance()).nextId();
//        return snowflakeIdWorker.nextId();
    }


}
