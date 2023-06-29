package com.huatec.iot.common.utils;

import cn.hutool.json.JSONUtil;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 14:15
 **/
public class JsonUtils {
    /**
     * 判断是否为json
     * @param str
     * @return
     */
    public static Boolean isJson(String str) {
        try {
            JSONUtil.parseObj(str);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

}
