package com.huatec.iot.iotservice.pojo.qry;

import com.huatec.iot.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分类Qry模型")
public class CategorysQry extends PageParam {

    @ApiModelProperty(value = "分类key")
    private String categoryKey;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "父ID")
    private Integer superId;

}