package com.huatec.iot.common.utils;

import java.util.Locale;
import java.util.UUID;

/**
 * @program: huatec-iot-api
 * @description: UUID工具类
 * @author: jiangtaohou
 * @create: 2023-04-13 14:15
 **/
public class UuidUtils {
    public static String getUuid() {
        return UUID.randomUUID().toString().toUpperCase(Locale.ROOT).replaceAll("-", "").toLowerCase();
    }

    public static String generateProductId() {
        return "pro" + getUuid().substring(0, 16);
    }

    public static String generateDeviceId() {
        return "dev" + getUuid().substring(0, 16);
    }

}
