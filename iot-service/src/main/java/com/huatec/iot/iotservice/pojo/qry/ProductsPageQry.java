package com.huatec.iot.iotservice.pojo.qry;

import com.huatec.iot.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品Qry模型")
public class ProductsPageQry extends PageParam {

    @ApiModelProperty(value = "产品名称")
    private String productName;

}