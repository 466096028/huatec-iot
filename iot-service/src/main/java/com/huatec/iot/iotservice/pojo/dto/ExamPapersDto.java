package com.huatec.iot.iotservice.pojo.dto;

import cn.hutool.json.JSONUtil;
import com.huatec.iot.common.domain.spec.entity.DoubleSpec;
import com.huatec.iot.iotservice.pojo.vo.ExamQuestionVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("试卷VO模型")
public class ExamPapersDto {
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

    /**
     * 状态 0：启用，1：未启用
     */
    @ApiModelProperty(value = "状态 0：启用，1：未启用")
    private Integer status;

    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer score;

    private List<ExamQuestionVo> examQuestionVoList;


    public static void main(String[] args) {
        ExamPapersDto ee = new ExamPapersDto();
        ee.setId("");
        ee.setName("试卷1");
        ee.setContent("试卷内容");
        ee.setScore(100);
        ee.setStatus(0);


        ExamQuestionDto wq = new ExamQuestionDto();
        wq.setQuestionId("");
        wq.setPaperId("");
        wq.setName("题目1");
        wq.setContent("内容1");
        wq.setScore(10);
        wq.setSort(0);
        wq.setType((short)0);

        ExamQuestionOptionsDto ww = new ExamQuestionOptionsDto();
        ww.setId("");
        ww.setQuestionId("");
        ww.setContent("adweqr");
        ww.setIsCorrect(1);

        String strr = JSONUtil.toJsonStr(ww);
        System.out.println(strr);
    }


}

