package com.huatec.iot.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: huatec-iot-api
 * @description: Mqtt配置
 * @author: jiangtaohou
 * @create: 2023-04-19 09:25
 **/
@Configuration
@Data
public class MqttConfig {
    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.port}")
    private Integer port;

    @Value("${spring.mqtt.node-name}")
    private String nodeName;

    @Value("${spring.mqtt.client-id}")
    private String clientId;

}
