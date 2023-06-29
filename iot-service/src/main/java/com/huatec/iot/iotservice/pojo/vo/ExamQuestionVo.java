package com.huatec.iot.iotservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel("试题VO模型")
public class ExamQuestionVo {

    /**
     * 主键
     */
    @ApiModelProperty(value = "ID")
    private String questionId;

    /**
     *
     */
    @ApiModelProperty(value = "类型 0:选择；1：操作")
    private Short type;

    /**
     *
     */
    @ApiModelProperty(value = "题目名称")
    private String name;

    /**
     * 题目内容
     */
    @ApiModelProperty(value = "题目内容")
    private String content;

    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer score;

    private List<ExamQuestionAttachmentVo> examQuestionAttachmentVoList;

    private List<ExamQuestionOptionsVo> examQuestionOptionsVoList;

}
