package com.huatec.iot.iotservice.listener;

import com.huatec.iot.common.constants.MQTopicGroupConstants;
import com.huatec.iot.common.pojo.MessageDto;
import com.huatec.iot.iotservice.service.DeviceEsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
public class PropertySetConsumerListener {
    @Autowired
    DeviceEsService deviceEsService;

    @Service
    @RocketMQMessageListener(topic = MQTopicGroupConstants.PROPERTY_SET_COMSUMER_TOPIC, consumerGroup = MQTopicGroupConstants.PROPERTY_SET_COMSUMER_GROUP)
    public class propertySetConsumer implements RocketMQListener<MessageDto> {
        // 监听到消息就会执行此方法
        @SneakyThrows
        @Override
        public void onMessage(MessageDto messageDto) {
            messageDto.setCreateTime(new Date());
            log.info("【MQ监听属性设置消息】监听到消息：str={}", messageDto);

            deviceEsService.insertData(messageDto.getProductKey() + "." + messageDto.getDeviceKey(), messageDto);
        }
    }

}
