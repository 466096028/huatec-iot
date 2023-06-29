package com.huatec.iot.iotservice.pojo.qry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("bytevQry模型")
public class ByteVParamsQry {
    @ApiModelProperty(value = "产品Key", required = true)
    @NotBlank(message = "产品Key不能为空")
    private String productKey;

    @ApiModelProperty(value = "设备Key", required = true)
    @NotBlank(message = "设备Key不能为空")
    private String deviceKey;

    @ApiModelProperty(value = "功能标识符")
    private String abilityIdentifier;
}