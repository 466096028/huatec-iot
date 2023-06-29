package com.huatec.iot.iotservice.pojo.qry;

import com.huatec.iot.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("试卷")
public class ExamPaperPageQry extends PageParam {

    @ApiModelProperty(value = "试卷名")
    private String name;

    @ApiModelProperty(value = "试卷ID")
    private String id;

}
