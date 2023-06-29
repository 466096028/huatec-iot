package com.huatec.iot.iotservice.pojo.qry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("试卷")
public class ExamPaperQry {
    @ApiModelProperty(value = "试卷ID")
    private String id;
    @ApiModelProperty(value = "类型")
    private String type;
}
