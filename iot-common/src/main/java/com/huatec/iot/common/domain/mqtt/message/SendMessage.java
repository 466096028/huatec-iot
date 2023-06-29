package com.huatec.iot.common.domain.mqtt.message;


import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @program: huatec-iot-api
 * @description: 各种消息处理接口
 * @author: jiangtaohou
 * @create: 2023-04-21 16:31
 **/
public interface SendMessage {
    /**
     * 匹配路由
     * @return
     */
    Boolean match(String topic, MqttMessage message);

    /**
     * 执行
     * @param topic
     * @param message
     * @return
     */
    void execute(String topic, MqttMessage message);

}
