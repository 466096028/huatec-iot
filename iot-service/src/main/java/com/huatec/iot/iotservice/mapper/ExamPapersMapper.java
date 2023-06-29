package com.huatec.iot.iotservice.mapper;

import com.huatec.iot.common.entity.ExamPapers;
import com.huatec.iot.common.entity.ExamQuestion;
import com.huatec.iot.common.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface ExamPapersMapper extends BaseMapper<ExamPapers> {

    ExamPapers getExamPapers(String id);

    List<ExamQuestion> getExamQuestionListByPapersId(Map paraMap);
}