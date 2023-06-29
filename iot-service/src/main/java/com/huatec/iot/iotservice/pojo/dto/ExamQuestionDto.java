package com.huatec.iot.iotservice.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("试题VO模型")
public class ExamQuestionDto {

    /**
     * 主键
     */
    @ApiModelProperty(value = "ID")
    private String questionId;


    @ApiModelProperty(value = "试卷ID")
    private String paperId;
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

    /**
     * 分数
     */
    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "试题选项")
    private List<ExamQuestionOptionsDto> examQuestionOptionsDtoList;

    @ApiModelProperty(value = "图片列表")
    private List<String> imgUlrList;

}
