package com.wangqian.utils.dac;

/**
 * twitter的snowflake算法
 * 分布式系统中，全局唯一ID的场景，生产自增ID。
 */
public class SnowflakeIdWorker {

    /**
     * 开始时间戳 (2018-1-18)
     * 起始的时间戳，可以修改为服务第一次启动的时间
     */
    private static final long TWEPOCH = 1516260290130L;

    /**
     * 机器ID所占的位数
     */
    private static final long WORKERID_BITS = 5L;

    /**
     * 数据中心ID所占的位数
     */
    private static final long DATACENTERID_BITS = 5L;

    /**
     * 支持的最大机器ID 此算法可以很快的计算出几位二进制数所能表示的最大十进制数
     */
    private static final long MAX_WORKERID = ~(-1L << WORKERID_BITS);

    /**
     * 支持最大的数据中心ID
     */
    private static final long MAX_DATACENTERID = ~(-1L << DATACENTERID_BITS);

    /**
     * 序列在ID中占的位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 机器ID左移12位
     */
    private static final long WORKERID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据中心ID左移(12 + 5)位
     */
    private static final long DATACENTERID_SHIFT = SEQUENCE_BITS + WORKERID_BITS;

    /**
     * 时间戳左移(12 + 5 + 5)位
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKERID_BITS + DATACENTERID_BITS;

    /**
     * 生成序列的掩码(4095)
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 工作机器(0-31)
     */
    private long workerId;

    /**
     * 数据中心ID(0-31)
     */
    private long datacenterId;

    /**
     * 毫秒内序列(0-4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;


    /**
     * 建议通过单利模式获取
     * 分布式部署服务时，数据中心标识和机器工作标识作为联合键必须唯一
     *
     * @param workerId     机器ID
     * @param datacenterId 数据中心ID
     */
    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > MAX_WORKERID || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", MAX_WORKERID));
        }

        if (datacenterId > MAX_DATACENTERID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenterId can't be greater than %d or less than 0", MAX_DATACENTERID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }


    /**
     * 获取下一个ID
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上次ID生成时的时间戳, 说明系统时钟被回退过.
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果当前时间和上次获取ID生成的时间戳一致, 则进行毫秒内序列.
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;

            //毫秒内序列溢出
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - TWEPOCH) << TIMESTAMP_SHIFT)
                | (datacenterId << DATACENTERID_SHIFT)
                | (workerId << WORKERID_SHIFT)
                | sequence;

    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒, 直到获取新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前的时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

}
