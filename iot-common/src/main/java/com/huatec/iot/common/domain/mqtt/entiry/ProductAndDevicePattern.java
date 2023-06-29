package com.huatec.iot.common.domain.mqtt.entiry;

import lombok.Data;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-22 16:38
 **/
@Data
public class ProductAndDevicePattern {
    private String productKey;
    private String deviceKey;
}
