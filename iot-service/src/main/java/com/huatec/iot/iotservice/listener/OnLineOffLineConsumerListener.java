package com.huatec.iot.iotservice.listener;

import com.huatec.iot.common.constants.MQTopicGroupConstants;
import com.huatec.iot.common.domain.mqtt.MqttServiceClient;
import com.huatec.iot.common.entity.Devices;
import com.huatec.iot.common.enums.DeviceStatusEnums;
import com.huatec.iot.common.enums.TopicEnums;
import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.common.utils.StrUtils;
import com.huatec.iot.iotservice.service.DeviceService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-20 19:42
 **/
@Slf4j
@Component
public class OnLineOffLineConsumerListener {
    @Autowired
    DeviceService deviceService;

    @Autowired
    MqttServiceClient mqttServiceClient;

    @Service
    @RocketMQMessageListener(topic = MQTopicGroupConstants.DEVICE_ONLINE_TOPIC, consumerGroup = MQTopicGroupConstants.DEVICE_ONLINE_CONSUMER_GROUP)
    public class OnLineConsumer implements RocketMQListener<MessageDto> {
        // 监听到消息就会执行此方法
        @SneakyThrows
        @Override
        public void onMessage(MessageDto messageDto) {
            log.info("【MQ监听上线消息】监听到上线消息：str={}", messageDto);

            if (StringUtils.isNotBlank(messageDto.getProductKey()) && StringUtils.isNotBlank(messageDto.getDeviceKey())) {
                mqttServiceClient.subscribe(StrUtils.transitionSysTopic1(TopicEnums.CLIENT_PROPERTY_POST_EVENT.getCode(), messageDto.getProductKey(), messageDto.getDeviceKey()), 0);
                mqttServiceClient.subscribe(StrUtils.transitionSysTopic1(TopicEnums.CLIENT_PROPERTY_SET_SERVICE.getCode(), messageDto.getProductKey(), messageDto.getDeviceKey()), 0);
            }


            Devices devices = new Devices();
            if (StringUtils.isNotBlank(messageDto.getProductKey()) && StringUtils.isNotBlank(messageDto.getDeviceKey())) {
                devices.setProductKey(messageDto.getProductKey());
                devices.setDeviceKey(messageDto.getDeviceKey());

                devices.setDeviceStatus((byte) DeviceStatusEnums.ACTIVATED_ONLINE_STATUS.getCode());
                devices.setActivationTime(new Date());
                devices.setLastActiveTime(new Date());
                devices.setUpdateTime(new Date());
                deviceService.updateByProductKeyAndDeviceKey(devices);
            }
        }
    }

    @Service
    @RocketMQMessageListener(topic = MQTopicGroupConstants.DEVICE_OFFLINE_TOPIC, consumerGroup = MQTopicGroupConstants.DEVICE_OFFLINE_CONSUMER_GROUP)
    public class OffLineConsumer implements RocketMQListener<MessageDto> {
        // 监听到消息就会执行此方法
        @SneakyThrows
        @Override
        public void onMessage(MessageDto messageDto) {
            log.info("【MQ监听下线消息】监听到下线消息：str={}", messageDto);

            if (StringUtils.isNotBlank(messageDto.getProductKey()) && StringUtils.isNotBlank(messageDto.getDeviceKey())) {
                mqttServiceClient.unSubscribe(StrUtils.transitionSysTopic1(TopicEnums.CLIENT_PROPERTY_POST_EVENT.getCode(), messageDto.getProductKey(), messageDto.getDeviceKey()));
                mqttServiceClient.unSubscribe(StrUtils.transitionSysTopic1(TopicEnums.CLIENT_PROPERTY_SET_SERVICE.getCode(), messageDto.getProductKey(), messageDto.getDeviceKey()));
            }


            Devices devices = new Devices();
            devices.setProductKey(messageDto.getProductKey());
            devices.setDeviceKey(messageDto.getDeviceKey());

            devices.setDeviceStatus((byte) DeviceStatusEnums.ACTIVATED_OFFLINE_STATUS.getCode());
            devices.setUpdateTime(new Date());
            deviceService.updateByProductKeyAndDeviceKey(devices);

        }
    }
}
