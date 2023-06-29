package com.huatec.iot.common.domain.mqtt.message;

import com.huatec.iot.common.config.MQProducerConfig;
import com.huatec.iot.common.constants.MQTopicGroupConstants;
import com.huatec.iot.common.domain.mqtt.entiry.ProductAndDevicePattern;
import com.huatec.iot.common.enums.TopicEnums;
import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.common.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: huatec-iot-api
 * @description: 属性设置主题
 * @author: jiangtaohou
 * @create: 2023-04-21 16:31
 **/
@Component
@Slf4j
public class PropertySetMessage implements SendMessage {

    @Autowired
    MQProducerConfig mqProducerConfig;

    @Override
    public Boolean match(String topic, MqttMessage message) {
        boolean matches = topic.matches(StrUtils.transitionSysTopic(TopicEnums.CLIENT_PROPERTY_SET_SERVICE.getCode(), "(.*)"));

        log.info("【属性设置下行状态发送消息】判断匹配主题，topic：{}，message：{}，result：{}", topic, message, matches);
        return matches;
    }

    @Override
    public void execute(String topic, MqttMessage message) {
        // log.info("【属性设置下行状态发送消息】执行发送，topic：{}，message：{}", topic, message);
        // try {
        //     // 组装消息对象
        //     MessageDto messageDto = new MessageDto();
        //     messageDto.setTopic(topic);
        //     messageDto.setQos(message.getQos());
        //     messageDto.setMqttMessageId(String.valueOf(message.getId()));
        //     messageDto.setMessage(message.toString());
        //
        //     ProductAndDevicePattern productAndDevicePattern = StrUtils.topicAnalysis(topic, TopicEnums.CLIENT_PROPERTY_SET_SERVICE.getCode());
        //     messageDto.setProductKey(productAndDevicePattern.getProductKey());
        //     messageDto.setDeviceKey(productAndDevicePattern.getDeviceKey());
        //     log.info("【属性设置下行状态发送消息】生成传输入对象，topic：{}，message：{}，messageDto：{}", topic, message, messageDto);
        //
        //     mqProducerConfig.sendMsg(MQTopicGroupConstants.PROPERTY_SET_COMSUMER_TOPIC, messageDto);
        //
        // } catch (Exception e) {
        //     log.error("【属性设置下行状态发送消息】执行发送异常 ，topic：{}，message：{}，messageDto：{}, exception：{}", topic, message, e.getMessage());
        // }
    }
}
