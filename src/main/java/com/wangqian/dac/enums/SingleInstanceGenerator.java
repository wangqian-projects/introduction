package com.wangqian.dac.enums;

import com.wangqian.utils.dac.SnowflakeIdWorker;
import config.ConfigExact;

/**
 * <p>ClassName: SingleInstanceGenerator</p>
 * <p>Description: 枚举模式生产单例</p>
 *
 * @author wangqian
 * @date 2018-01-19 17:39
 */
public enum SingleInstanceGenerator {
    SNOWFLAKE_ID_WORKER(0, "自增ID");

    private int id;
    private String text;
    private SnowflakeIdWorker snowflake;

    private SingleInstanceGenerator(int id, String text) {
        this.id = id;
        this.text = text;
        snowflake = new SnowflakeIdWorker(ConfigExact.getWorkerId(), ConfigExact.getDatacenterId());
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public SnowflakeIdWorker getSnowflakeIdWorker() {
        return snowflake;
    }

}
