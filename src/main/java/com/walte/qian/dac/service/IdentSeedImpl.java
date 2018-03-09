package com.walte.qian.dac.service;

import com.walte.qian.dac.service.spi.IIdentSeedService;
import com.walte.qian.dac.util.SnowflakeIdWorker;
import config.ConfigExact;

import java.util.UUID;

/**
 * <p>ClassName: IdentSeedImpl</p>
 * <p>Description: ID和流水号管理类</p>
 * 
 * @author wangqian
 * @date 2018-03-09 18:01
 */
public class IdentSeedImpl implements IIdentSeedService{

    private final static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(ConfigExact.getWorkerId(), ConfigExact.getDatacenterId());

    @Override
    public long nextSnowflakeId() {
        return snowflakeIdWorker.nextId();
    }

    //TODO ID获取和流水号管理

    @Override
    public long nextId(UUID uuid, int count) {
        return 0;
    }

    @Override
    public long nextSerialNumber(String key) {
        return 0;
    }

    @Override
    public long pickSerialNumber(String key) {
        return 0;
    }
}
