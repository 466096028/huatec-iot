package com.huatec.iot.iotservice.pojo.vo;

import com.huatec.iot.common.pojo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("类型VO模型")
public class CategorysVo extends BaseVo {

    @ApiModelProperty(value = "ID", position = 1)
    private Integer id;

    @ApiModelProperty(value = "类型key", position = 2)
    private String categoryKey;

    @ApiModelProperty(value = "类型名称", position = 3)
    private String categoryName;

    @ApiModelProperty(value = "父ID", position = 4)
    private Integer superId;

    @ApiModelProperty(value = "备注", position = 5)
    private String remark;

    @ApiModelProperty(value = "是否删除 0:未删除;1:已删除", position = 6)
    private Integer isDel;

}