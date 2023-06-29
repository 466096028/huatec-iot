package com.huatec.iot.iotservice.mapper;

import com.huatec.iot.common.entity.ExamQuestionOptions;
import com.huatec.iot.common.mapper.BaseMapper;

import java.util.List;

public interface ExamQuestionOptionsMapper extends BaseMapper<ExamQuestionOptions> {
    List<ExamQuestionOptions> getOptionsByQuestionId(String questionId);
}