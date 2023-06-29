package com.huatec.iot.iotservice.pojo.qry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设备Qry模型")
public class DevicesQry {
    @ApiModelProperty(value = "产品ID", position = 3)
    private Integer productId;

}