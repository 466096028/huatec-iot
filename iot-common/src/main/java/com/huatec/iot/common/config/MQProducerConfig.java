package com.huatec.iot.common.config;

import com.alibaba.fastjson.JSON;
import com.huatec.iot.common.pojo.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @program: huatec-iot-api
 * @description: MQ生产者
 * @author: jiangtaohou
 * @create: 2023-04-20 15:36
 **/
@Slf4j
@Configuration
public class MQProducerConfig {

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer messageTimeOut;

    // 直接注入使用，用于发送消息到broker服务器
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 普通发送（这里的参数对象User可以随意定义，可以发送个对象，也可以是字符串等）
     */
    public void send(String topic, MessageDto msgBody) {
        log.info("【发送MQ消息】普通消息，topic={}，msgBody={}", topic, msgBody);
        rocketMQTemplate.convertAndSend(topic, msgBody);
//        rocketMQTemplate.send(topic + ":tag1", MessageBuilder.withPayload(msgBody).build()); // 等价于上面一行
    }

    /**
     * 发送同步消息（阻塞当前线程，等待broker响应发送结果，这样不太容易丢失消息）
     * （msgBody也可以是对象，sendResult为返回的发送结果）
     */
    public SendResult sendMsg(String topic, MessageDto msgBody) {
        SendResult sendResult = rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msgBody).build());
        log.info("【发送MQ消息】同步消息，topic={}，msgBody={}，sendResult={}", topic, msgBody, JSON.toJSONString(sendResult));
        return sendResult;
    }

    /**
     * 发送异步消息（通过线程池执行发送到broker的消息任务，执行完后回调：在SendCallback中可处理相关成功失败时的逻辑）
     * （适合对响应时间敏感的业务场景）
     */
    public void sendAsyncMsg(String topic, MessageDto msgBody) {
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(msgBody).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 处理消息发送成功逻辑
                log.info("【发送MQ消息】异步消息-成功，topic={}，sendResult={}， msgBody：{}", topic, JSON.toJSONString(sendResult), msgBody);
            }

            @Override
            public void onException(Throwable throwable) {
                // 处理消息发送异常逻辑
                log.error("【发送MQ消息】异步消息-异常，topic={}， msgBody：{}", topic, msgBody);
            }
        });
    }

    /**
     * 发送延时消息（上面的发送同步消息，delayLevel的值就为0，因为不延时）
     * 在start版本中 延时消息一共分为18个等级分别为：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    public void sendDelayMsg(String topic, MessageDto msgBody, int delayLevel) {
        log.info("【发送MQ消息】延时消息，topic={}，msgBody：{}，delayLevel：{}", topic, msgBody, delayLevel);
        rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msgBody).build(), messageTimeOut, delayLevel);
    }

    /**
     * 发送单向消息（只负责发送消息，不等待应答，不关心发送结果，如日志）
     */
    public void sendOneWayMsg(String topic, MessageDto msgBody) {
        log.info("【发送MQ消息】单向消息，topic={}，msgBody", topic, msgBody);
        rocketMQTemplate.sendOneWay(topic, MessageBuilder.withPayload(msgBody).build());
    }

    /**
     * 发送带tag的消息，直接在topic后面加上":tag"
     */
    public SendResult sendTagMsg(String topic, MessageDto msgBody) {
        log.info("【发送MQ消息】带tag的消息，topic={}，msgBody", topic, msgBody);
        return rocketMQTemplate.syncSend(topic + ":tag2", MessageBuilder.withPayload(msgBody).build());
    }

}
