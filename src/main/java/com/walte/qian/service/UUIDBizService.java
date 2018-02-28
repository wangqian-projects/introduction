package com.walte.qian.service;

import com.walte.qian.utils.sys.UUIDUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ClassName: UUIDBizService</p>
 * <p>Description: UUID 业务层</p>
 * 
 * @author wangqian
 * @date 2018-02-27 16:25
 */
public class UUIDBizService {

    public String generateUuid(String version) {
        if("1".equals(version)) {
            return UUIDUtil.getRandomUUID().toString();
        } else if ("4".equals(version)) {
            return UUIDUtil.getRandomUUID().toString();
        }
        return "Generating UUID failed";
    }

    public List<String> generateBulkUuid(String version, int howMany, String hyphen) {

        List<String> uuidList = new ArrayList<>(howMany);
        if ("0".equals(hyphen)) {
            for (int i = 0; i < howMany; i++) {
                uuidList.add(UUIDUtil.getRandomUUID().toString());
            }
        } else {
            for (int i = 0; i < howMany; i++) {
                uuidList.add(UUIDUtil.getUUID());
            }
        }
        return uuidList;
    }
    
}
