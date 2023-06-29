package com.huatec.iot.iotservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设备和产品VO模型")
public class DevicesProductsVo {
    @ApiModelProperty(value = "设备Vo", position = 1)
    private DevicesVo devicesVo;

    @ApiModelProperty(value = "产品Vo", position = 2)
    private ProductsVo productsVo;

}