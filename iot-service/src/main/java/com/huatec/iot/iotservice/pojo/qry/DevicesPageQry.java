package com.huatec.iot.iotservice.pojo.qry;

import com.huatec.iot.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设备分页Qry模型")
public class DevicesPageQry extends PageParam {
    @ApiModelProperty(value = "设备名称", position = 1)
    private String deviceName;

    @ApiModelProperty(value = "设备key", position = 2)
    private String deviceKey;

    @ApiModelProperty(value = "产品ID", position = 3)
    private Integer productId;

}