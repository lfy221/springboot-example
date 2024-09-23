package com.neu.java.spring.springboot.hikari.common.util;

import java.util.UUID;

public class UUIDGenerator {

    public static String getUUID() {
        // 去掉"-"符号
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获得指定数量的UUID
     * @param number
     * @return
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }

        String[] uuids = new String[number];
        for (int i = 0; i < uuids.length; i++) {
            uuids[i] = getUUID();
        }
        return uuids;
    }
}
