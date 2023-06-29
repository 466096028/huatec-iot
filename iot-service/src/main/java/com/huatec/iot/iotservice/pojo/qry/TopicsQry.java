package com.huatec.iot.iotservice.pojo.qry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("通信TopicQry模型")
public class TopicsQry {

    @ApiModelProperty(value = "产品Key")
    private String productKey;

    @ApiModelProperty(value = "设备Key")
    private String deviceKey;

}