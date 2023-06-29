package com.huatec.iot.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 11:50
 **/
@Data
@ApiModel("分页参数")
public class PageParam {
    @ApiModelProperty(value = "当前页",required = true, example = "1")
    private int pageIndex = 1;
    @ApiModelProperty(value = "每页记录数",required = true, example = "10")
    private int pageSize = 10;
}
