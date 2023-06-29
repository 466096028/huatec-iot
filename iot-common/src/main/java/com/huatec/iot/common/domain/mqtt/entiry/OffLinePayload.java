package com.huatec.iot.common.domain.mqtt.entiry;

import lombok.Data;

/**
 * @program: huatec-iot-api
 * @description: 有效载荷
 * @author: jiangtaohou
 * @create: 2023-04-17 20:18
 **/
@Data
public class OffLinePayload {
    // 客户端ID
    String clientid = "";

    // 下线时间
    Integer disconnected_at = 0;

    // IP地址
    String ipaddress = "";

    // 用户名
    String username = "";
}
