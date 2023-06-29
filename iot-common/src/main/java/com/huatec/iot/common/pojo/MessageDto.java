package com.huatec.iot.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-21 09:50
 **/
@Data
public class MessageDto {
    // 主题
    String topic = "";

    // 客户端ID
    String clientid = "";

    // 产品KEY
    String productKey = "";

    // 设备KEY
    String deviceKey = "";

    // IP地址
    String ipaddress = "";

    // mqtt消息ID
    String mqttMessageId = "";

    // 消息
    String message;

    // 上线时间
    Integer connected_at = 0;

    // 下线时间
    Integer disconnected_at = 0;

    // 用户名
    String username = "";

    //服务质量
    Integer qos = 0;

    //添加时间
    Date createTime;

}
