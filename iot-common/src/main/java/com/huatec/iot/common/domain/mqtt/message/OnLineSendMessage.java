package com.huatec.iot.common.domain.mqtt.message;

import cn.hutool.json.JSONUtil;
import com.huatec.iot.common.config.MQProducerConfig;
import com.huatec.iot.common.constants.MQTopicGroupConstants;
import com.huatec.iot.common.domain.mqtt.MqttServiceClient;
import com.huatec.iot.common.domain.mqtt.entiry.OnLinePayload;
import com.huatec.iot.common.enums.TopicEnums;
import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.common.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: huatec-iot-api
 * @description: 上线主题
 * @author: jiangtaohou
 * @create: 2023-04-21 16:31
 **/
@Component
@Slf4j
public class OnLineSendMessage implements SendMessage {

    @Autowired
    MQProducerConfig mqProducerConfig;

    @Autowired
    MqttServiceClient mqttServiceClient;

    @Override
    public Boolean match(String topic, MqttMessage message) {
        boolean matches = topic.matches(StrUtils.transitionMqttSysTopic(TopicEnums.CLIENT_ONLINE_EVENT.getCode()));

        log.info("【上线状态发送消息】判断匹配主题，topic：{}，message：{}，result：{}", topic, message, matches);
        return matches;
    }

    @Override
    public void execute(String topic, MqttMessage message) {
        log.info("【上线状态发送消息】执行发送，topic：{}，message：{}", topic, message);
        try {
            MessageDto messageDto = new MessageDto();
            OnLinePayload onLinePayload = JSONUtil.toBean(message.toString(), OnLinePayload.class);
            messageDto.setQos(message.getQos());
            messageDto.setMqttMessageId(String.valueOf(message.getId()));
            messageDto.setClientid(onLinePayload.getClientid());
            messageDto.setConnected_at(onLinePayload.getConnected_at());
            messageDto.setUsername(onLinePayload.getUsername());
            messageDto.setIpaddress(onLinePayload.getIpaddress());

            // 判断客户端是否存在
            if (StringUtils.isNotBlank(messageDto.getClientid())) {
                String[] split = messageDto.getClientid().split("\\.");
                if (split.length >= 1) {
                    messageDto.setDeviceKey(split[0]);
                }
                if (split.length >= 2) {
                    messageDto.setProductKey(split[1]);
                }

            }
            log.info("【上线状态发送消息】生成传输入对象，topic：{}，message：{}，messageDto：{}", topic, message, messageDto);
            mqProducerConfig.sendMsg(MQTopicGroupConstants.DEVICE_ONLINE_TOPIC, messageDto);

        } catch (Exception e) {
            log.error("【上线状态发送消息】执行发送异常 ，topic：{}，message：{}，messageDto：{}, exception：{}", topic, message, e.getMessage());
        }

    }
}
