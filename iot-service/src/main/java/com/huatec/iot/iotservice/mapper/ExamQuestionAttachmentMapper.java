package com.huatec.iot.iotservice.mapper;

import com.huatec.iot.common.entity.ExamQuestionAttachment;
import com.huatec.iot.common.mapper.BaseMapper;

import java.util.List;

public interface ExamQuestionAttachmentMapper extends BaseMapper<ExamQuestionAttachment> {
    List<ExamQuestionAttachment> getAttachmentListByQuestionId(String questionId);
}