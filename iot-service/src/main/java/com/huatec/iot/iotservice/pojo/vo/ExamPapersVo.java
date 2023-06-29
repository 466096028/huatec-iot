package com.huatec.iot.iotservice.pojo.vo;

import com.huatec.iot.common.entity.ExamQuestion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
@ApiModel("试卷VO模型")
public class ExamPapersVo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "试卷名")
    private String name;

    /**
     * 试卷内容
     */
    @ApiModelProperty(value = "试卷内容")
    private String content;


    private Integer status;

    /**
     * 状态 0：启用，1：未启用
     */
    @ApiModelProperty(value = "状态 0：启用，1：未启用")
    private Integer isCorrect;
    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer score;

    private List<ExamQuestionVo> examQuestionVoList;
}
