package com.huatec.iot.iotservice.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel("MQTT连接参数VO模型")
@ToString
public class MqttConnectInfoVo {
    @JsonProperty
    @ApiModelProperty(value = "客户端id", position = 1)
    private String clientid;

    @ApiModelProperty(value = "用户名称", position = 2)
    private String username;

    @ApiModelProperty(value = "密码", position = 3)
    private String passwd;

    @ApiModelProperty(value = "MQTT地址", position = 4)
    private String mqttHostUrl;

    @ApiModelProperty(value = "端口", position = 5)
    private String port;

    @JsonProperty
    @ApiModelProperty(value = "产品key", position = 6)
    private String ProductKey;

    @JsonProperty
    @ApiModelProperty(value = "设备名称", position = 7)
    private String DeviceName;

    @JsonProperty
    @ApiModelProperty(value = "私密串", position = 8)
    private String DeviceSecret;

    @JsonProperty
    @ApiModelProperty(value = "发布主题", position = 9)
    private String PublishTopic;

    @JsonProperty
    @ApiModelProperty(value = "订阅主题", position = 10)
    private String SubscribeTopic;

    @JsonIgnore
    public String getClientid() {
        return clientid;
    }

    @JsonIgnore
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getMqttHostUrl() {
        return mqttHostUrl;
    }

    public void setMqttHostUrl(String mqttHostUrl) {
        this.mqttHostUrl = mqttHostUrl;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @JsonIgnore
    public String getProductKey() {
        return ProductKey;
    }

    @JsonIgnore
    public void setProductKey(String productKey) {
        ProductKey = productKey;
    }

    @JsonIgnore
    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    @JsonIgnore
    public String getDeviceSecret() {
        return DeviceSecret;
    }

    @JsonIgnore
    public void setDeviceSecret(String deviceSecret) {
        DeviceSecret = deviceSecret;
    }

    @JsonIgnore
    public String getPublishTopic() {
        return PublishTopic;
    }

    @JsonIgnore
    public void setPublishTopic(String publishTopic) {
        PublishTopic = publishTopic;
    }

    @JsonIgnore
    public String getSubscribeTopic() {
        return SubscribeTopic;
    }

    @JsonIgnore
    public void setSubscribeTopic(String subscribeTopic) {
        SubscribeTopic = subscribeTopic;
    }
}