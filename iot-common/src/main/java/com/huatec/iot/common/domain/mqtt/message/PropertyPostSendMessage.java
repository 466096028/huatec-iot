package com.huatec.iot.common.domain.mqtt.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huatec.iot.common.config.MQProducerConfig;
import com.huatec.iot.common.constants.MQTopicGroupConstants;
import com.huatec.iot.common.domain.mqtt.entiry.ProductAndDevicePattern;
import com.huatec.iot.common.enums.TopicEnums;
import com.huatec.iot.common.pojo.DeviceMessageDto;
import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.common.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @program: huatec-iot-api
 * @description: 属性上报主题
 * @author: jiangtaohou
 * @create: 2023-04-21 16:31
 **/
@Component
@Slf4j
public class PropertyPostSendMessage implements SendMessage {

    @Autowired
    MQProducerConfig mqProducerConfig;

    @Override
    public Boolean match(String topic, MqttMessage message) {
        boolean matches = topic.matches(StrUtils.transitionSysTopic(TopicEnums.CLIENT_PROPERTY_POST_EVENT.getCode(), "(.*)"));

        log.info("【属性上报上行状态发送消息】判断匹配主题，topic：{}，message：{}，result：{}", topic, message, matches);
        return matches;
    }

    @Override
    public void execute(String topic, MqttMessage message) {
        log.info("【属性上报上行状态发送消息】执行发送，topic：{}，message：{}", topic, message);
        try {
            MessageDto messageDto = new MessageDto();
            messageDto.setTopic(topic);
            messageDto.setQos(message.getQos());
            messageDto.setMqttMessageId(String.valueOf(message.getId()));
            messageDto.setMessage(message.toString());

            if (StringUtils.isNotBlank(message.toString())) {
                // 检查payload是正确的json串
                DeviceMessageDto deviceMessageDto = JSONObject.parseObject(message.toString(), DeviceMessageDto.class);
                Map map = JSONObject.parseObject(deviceMessageDto.getParams(), Map.class);
                deviceMessageDto.setParams(JSON.toJSONString(map));
                messageDto.setMessage(JSON.toJSONString(deviceMessageDto));
                ProductAndDevicePattern productAndDevicePattern = StrUtils.topicAnalysis(topic, TopicEnums.CLIENT_PROPERTY_POST_EVENT.getCode());

                messageDto.setProductKey(productAndDevicePattern.getProductKey());
                messageDto.setDeviceKey(productAndDevicePattern.getDeviceKey());
                log.info("【属性上报上行状态发送消息】生成传输入对象，topic：{}，message：{}，messageDto：{}", topic, message, messageDto);
                mqProducerConfig.sendMsg(MQTopicGroupConstants.DEVICE_PROPERTY_POST_TOPIC, messageDto);
            }
        } catch (Exception e) {
            log.error("【属性上报上行状态发送消息】执行发送异常 ，topic：{}，message：{}，messageDto：{}, exception：{}", topic, message, e.getMessage());
        }
    }
}
