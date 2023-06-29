package com.huatec.iot.iotservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设备数VO模型")
public class ProductDeviceCountVo {

    @ApiModelProperty(value = "设备总数", position = 1)
    private int deviceTotal;

    @ApiModelProperty(value = "激活设备数", position = 2)
    private int activityDeviceCount;

    @ApiModelProperty(value = "在线设备数", position = 3)
    private int onLineDeviceCount;

}