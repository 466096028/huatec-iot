package com.huatec.iot.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 20:00
 **/

@Data
@ApiModel("基础VO模型")
public class BaseVo {

    @ApiModelProperty(value = "创建时间", position = 98)
    private Date createTime;

    @ApiModelProperty(value = "修改时间", position = 99)
    private Date updateTime;
}
