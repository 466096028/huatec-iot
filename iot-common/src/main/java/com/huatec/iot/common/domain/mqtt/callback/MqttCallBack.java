package com.huatec.iot.common.domain.mqtt.callback;

import com.huatec.iot.common.domain.mqtt.MqttServiceClient;
import com.huatec.iot.common.domain.mqtt.SendMessageFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @program: huatec-iot-api
 * @description: 消息发布客户端回调
 * @author: jiangtaohou
 * @create: 2023-04-17 17:09
 **/
@Configuration
@Slf4j
@Data
public class MqttCallBack implements MqttCallback {
    @Autowired
    SendMessageFactory sendMessageFactory;

    @Autowired
    MqttServiceClient mqttServiceClient;

    /**
     * 与服务器断开的回调
     */
    @Override
    public void connectionLost(Throwable cause) {
        log.info("【MQTT回调】服务器断开连接");
    }

    /**
     * 消息到达的回调
     */
    @Override
    public void messageArrived(String topic, MqttMessage message){
        try {
            log.info("【MQTT回调】消息到达，topic：{}，message：{}", topic, message);
            sendMessageFactory.messageTransmit(topic, message);
        } catch (Exception e) {
            log.error("【MQTT回调】消息到达异常，topic：{}，message：{}，exception：{}", topic, message, e.getMessage());
        }

    }

    /**
     * 消息发布成功的回调
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        IMqttAsyncClient client = token.getClient();
        log.info("【MQTT回调】消息发布成功，client：{}", client.getClientId());
    }
}

