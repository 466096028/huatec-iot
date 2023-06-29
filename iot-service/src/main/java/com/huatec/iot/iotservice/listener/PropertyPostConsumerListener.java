package com.huatec.iot.iotservice.listener;

import com.huatec.iot.common.config.MQProducerConfig;
import com.huatec.iot.common.constants.MQTopicGroupConstants;
import com.huatec.iot.common.entity.Devices;
import com.huatec.iot.common.enums.DeviceStatusEnums;
import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.common.utils.VUtils;
import com.huatec.iot.iotservice.service.DeviceEsService;
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
import java.util.Objects;
import java.util.Optional;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-20 19:42
 **/
@Slf4j
@Component
public class PropertyPostConsumerListener {
    @Autowired
    DeviceEsService deviceEsService;

    @Autowired
    DeviceService deviceService;

    @Service
    @RocketMQMessageListener(topic = MQTopicGroupConstants.DEVICE_PROPERTY_POST_TOPIC, consumerGroup = MQTopicGroupConstants.DEVICE_PROPERTY_POST_COMSUMER_GROUP)
    public class propertyPostConsumer implements RocketMQListener<MessageDto> {
        // 监听到消息就会执行此方法
        @SneakyThrows
        @Override
        public void onMessage(MessageDto messageDto) {
            messageDto.setCreateTime(new Date());
            log.info("【MQ监听属性上报消息】监听到消息，messageDto：{}", messageDto);
            if (StringUtils.isNotBlank(messageDto.getProductKey()) && StringUtils.isNotBlank(messageDto.getDeviceKey())) {
                Devices devices = new Devices();
                devices.setProductKey(messageDto.getProductKey());
                devices.setDeviceKey(messageDto.getDeviceKey());
                devices.setCreateUserId(-1);
                Optional<Devices> devices1 = Optional.ofNullable(deviceService.checkExists(devices));
                if (devices1.isPresent()) {
                    deviceEsService.insertData(messageDto.getProductKey() + "." + messageDto.getDeviceKey(), messageDto);
                } else {
                    log.info("【MQ监听属性上报消息】监听到消息，messageDto：{}， error：{}", messageDto, "没有找到设备");
                }
            }
        }
    }

}
