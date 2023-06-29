package com.huatec.iot.iotservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("附件VO模型")
public class ExamQuestionAttachmentVo {

    /**
     * 主键
     */
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 试题Id
     */
    @ApiModelProperty(value = "试题Id")
    private String questionId;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String fileName;

    /**
     * 附件网址
     */
    @ApiModelProperty(value = "附件网址")
    private String fileUrl;
}
