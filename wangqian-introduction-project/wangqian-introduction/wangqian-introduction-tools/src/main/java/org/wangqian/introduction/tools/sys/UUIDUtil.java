package org.wangqian.introduction.tools.sys;

import java.util.UUID;

/**
 * <p>ClassName: UUIDUtil</p>
 * <p>Description: UUID生产工具</p>
 * 
 * @author wangqian
 * @date 2018-01-24 11:48
 */
public class UUIDUtil {

    /**
     * 获取一个随机UUID
     *
     * @return UUID
     */
    public static UUID getRandomUUID() {
        return UUID.randomUUID();
    }

    /**
     * 以连接符分割UUID
     *
     * @param uuid UUID
     * @return String[]
     */
    private static String[] splitUUIDWithHyphen(UUID uuid) {
        return uuid.toString().split("-");
    }

    /**
     * 获取4个长度的十六进制的UUID
     *
     * @return String
     */
    public static String get4UUID() {
        return splitUUIDWithHyphen(getRandomUUID())[1];
    }

    /**
     * 获取8个长度的十六进制的UUID
     *
     * @return String
     */
    public static String get8UUID() {
        return splitUUIDWithHyphen(getRandomUUID())[0];
    }

    /**
     * 获取12个长度的十六进制的UUID
     *
     * @return String
     */
    public static String get12UUID() {
        String[] fragments = splitUUIDWithHyphen(getRandomUUID());
        return fragments[0] + fragments[1];
    }

    /**
     * 获取16个长度的十六进制的UUID
     *
     * @return String
     */
    public static String get16UUID() {
        String[] fragments = splitUUIDWithHyphen(getRandomUUID());
        return fragments[0] + fragments[1] + fragments[2];
    }

    /**
     * 获取20个长度的十六进制的UUID
     *
     * @return String
     */
    public static String get20UUID() {
        String[] fragments = splitUUIDWithHyphen(getRandomUUID());
        return fragments[0] + fragments[1] + fragments[2] + fragments[3];
    }

    /**
     * 获取24个长度的十六进制的UUID
     *
     * @return String
     */
    public static String get24UUID() {
        String[] fragments = splitUUIDWithHyphen(getRandomUUID());
        return fragments[0] + fragments[1] + fragments[2] + fragments[4];
    }

    /**
     * 获取32个长度的十六进制的UUID
     *
     * @return String
     */
    public static String get32UUID() {
        String[] uuids = splitUUIDWithHyphen(getRandomUUID());
        return uuids[0] + uuids[1] + uuids[2] + uuids[3] + uuids[4];
    }

    /**
     * 获取UUID(默认获取32位)
     *
     * @return String
     */
    public static String getUUID() {
        return get32UUID();
    }

    /**
     * 指定位数获取UUID(支持4/8/12/16/20/24/32位的UUID,超出范围则默认返回32位)
     *
     * @param l 位
     * @return String
     */
    public static String getUUID(int l) {

        switch (l) {
            case 4:
                return get4UUID();
            case 8:
                return get8UUID();
            case 12:
                return get12UUID();
            case 16:
                return get16UUID();
            case 20:
                return get20UUID();
            case 24:
                return get24UUID();
            case 32:
                return get32UUID();
            default:
                return get32UUID();
        }

    }

}
