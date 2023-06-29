package com.huatec.iot.iotservice.pojo.qry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@ApiModel("产品模型功能Qry模型")
public class ProductModuleAbilityQry {

    @ApiModelProperty(value = "产品ID", required = true)
    @Min(value = 1, message = "产品ID不能为空")
    private Integer productId;

    @ApiModelProperty(value = "设备ID")
    private Integer deviceId;

    @ApiModelProperty(value = "模型ID", required = true, example = "0")
    @Min(value = 0, message = "模型ID不能为空")
    private Integer moduleId;

}