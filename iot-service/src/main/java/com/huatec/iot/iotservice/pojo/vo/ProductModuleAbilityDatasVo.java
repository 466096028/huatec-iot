package com.huatec.iot.iotservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品模型功能数据VO模型")
public class ProductModuleAbilityDatasVo {
    @ApiModelProperty(value = "键", position = 1)
    private String key;

    @ApiModelProperty(value = "值", position = 2)
    private String value;

}