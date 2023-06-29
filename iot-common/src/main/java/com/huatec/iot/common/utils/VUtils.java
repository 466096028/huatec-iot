package com.huatec.iot.common.utils;

import com.huatec.iot.common.exception.ServiceException;
import com.huatec.iot.common.pojo.ThrowExceptionFunction;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 14:15
 **/
public class VUtils {
    /**
     * 如果参数为true抛出异常
     *
     * @param b
     * @return com.example.demo.func.ThrowExceptionFunction
     **/
    public static ThrowExceptionFunction isTure(boolean b) {

        return (errorMessage) -> {
            if (b) {
                throw new ServiceException(errorMessage);
            }
        };
    }
}
