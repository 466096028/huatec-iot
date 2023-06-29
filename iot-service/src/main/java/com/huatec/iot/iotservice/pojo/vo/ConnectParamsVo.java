package com.huatec.iot.iotservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("MQTT连接参数VO模型")
public class ConnectParamsVo {
    @ApiModelProperty(value = "客户端id", position = 1)
    private String clientId;

    @ApiModelProperty(value = "用户名称", position = 2)
    private String username;

    @ApiModelProperty(value = "密码", position = 3)
    private String passwd;

    @ApiModelProperty(value = "MQTT地址", position = 4)
    private String mqttUrl;

    @ApiModelProperty(value = "端口", position = 5)
    private Integer port;

}