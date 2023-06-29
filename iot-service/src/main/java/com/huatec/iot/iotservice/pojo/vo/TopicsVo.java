package com.huatec.iot.iotservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
@ApiModel("通信TopicVO模型")
public class TopicsVo {
    @ApiModelProperty(value = "ID", position = 1)
    private Integer id;

    @ApiModelProperty(value = "类型：1为基础，2为物模型，3自定义", position = 2)
    private Byte type;

    @ApiModelProperty(value = "类型名称", position = 2)
    private String typeName;

    @ApiModelProperty(value = "通信地址", position = 3)
    private String topicUrl;

    @ApiModelProperty(value = "功能类型名称", position = 4)
    private String abilityTypeName;

    @ApiModelProperty(value = "权限类型：1为发布，2为订阅", position = 5)
    private Byte authorizationType;

    @ApiModelProperty(value = "权限类型名称", position = 5)
    private String authorizationTypeName;

    @ApiModelProperty(value = "描述", position = 6)
    private String description;

    @ApiModelProperty(value = "状态:0为启用，1为禁用", position = 7)
    private Byte status;
}