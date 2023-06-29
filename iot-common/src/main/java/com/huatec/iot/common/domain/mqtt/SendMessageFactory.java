package com.huatec.iot.common.domain.mqtt;

import com.huatec.iot.common.domain.mqtt.message.SendMessage;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @program: huatec-iot-api
 * @description: 发送消息工场类
 * @author: jiangtaohou
 * @create: 2023-04-21 17:08
 **/
@Component
public class SendMessageFactory {

    @Autowired
    private Map<String, SendMessage> sendMessageMap;


    /**
     * 消息传输
     * @param topic
     * @param message
     */
    public void messageTransmit(String topic, MqttMessage message){
        SendMessage sendMessage = null;
        // 通过topic地址匹配到对应的对象
        for (SendMessage source : sendMessageMap.values()) {
            Boolean match = source.match(topic, message);
            if (match) {
                sendMessage = source;
            }
        }

        if (!Objects.isNull(sendMessage)) {
            // 执行消息
            sendMessage.execute(topic, message);
        }
    }
}