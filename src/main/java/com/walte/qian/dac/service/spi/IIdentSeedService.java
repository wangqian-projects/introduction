package com.walte.qian.dac.service.spi;

import java.util.UUID;

/**
 * <p>ClassName: IIdentSeedService</p>
 * <p>Description: ID和流水号管理接口</p>
 *
 * @author wangqian
 * @date 2018-03-09 17:50
 */
public interface IIdentSeedService {

    /**
     * 获取雪花算法的下一个ID
     * @return
     */
    public long nextSnowflakeId();


    /**
     * 获取下N个ID
     */
    public long nextId(UUID uuid, int count);


    /**
     * 获取下一个流水号
     *
     * @param key 唯一标识
     * @return long
     */
    public long nextSerialNumber(String key);


    /**
     * 获取当前流水号
     *
     * @param key 唯一标识
     * @return long
     */
    public long pickSerialNumber(String key);


}
