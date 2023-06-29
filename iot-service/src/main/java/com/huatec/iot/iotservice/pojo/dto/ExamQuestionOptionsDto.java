package com.huatec.iot.iotservice.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("选择题选项VO模型")
public class ExamQuestionOptionsDto {

    /**
     * 主键
     */
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 试题ID
     */
    @ApiModelProperty(value = "试题ID")
    private String questionId;

    /**
     * 选项内容
     */
    @ApiModelProperty(value = "选项内容")
    private String content;

    /**
     * 是否为正确选项
     */
    @ApiModelProperty(value = "是否为正确选项")
    private Integer isCorrect;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

}
