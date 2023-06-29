package com.huatec.iot.iotservice.pojo.vo;

import com.huatec.iot.common.pojo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("产品VO模型")
public class ProductsVo extends BaseVo {

    @ApiModelProperty(value = "ID", position = 1)
    private Integer id;

    @ApiModelProperty(value = "产品key", position = 2)
    private String productKey;

    @ApiModelProperty(value = "产品名称", position = 3)
    private String productName;

    @ApiModelProperty(value = "类型Id", position = 4)
    private Integer categoryId;

    @ApiModelProperty(value = "类型key", position = 5)
    private String categoryKey;

    @ApiModelProperty(value = "类型名称", position = 6)
    private String categoryName;

    @ApiModelProperty(value = "节点类型", position = 7)
    private Byte nodeType;

    @ApiModelProperty(value = "节点类型名称", position = 7)
    private String nodeTypeName;

    @ApiModelProperty(value = "连网方式", position = 8)
    private Byte netType;

    @ApiModelProperty(value = "连网方式名称", position = 8)
    private String netTypeName;

    @ApiModelProperty(value = "数据格式", position = 9)
    private Byte dataFormat;

    @ApiModelProperty(value = "数据格式名称", position = 9)
    private String dataFormatName;

    @ApiModelProperty(value = "描述", position = 10)
    private String description;

    @ApiModelProperty(value = "产品状态", position = 11)
    private Byte status;

    @ApiModelProperty(value = "设备数", position = 12)
    private Integer deviceCount;

}