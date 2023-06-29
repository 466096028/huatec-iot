package com.huatec.iot.iotservice.service;

import com.huatec.iot.iotservice.pojo.dto.ExamPapersDto;
import com.huatec.iot.iotservice.pojo.dto.ExamQuestionDto;
import com.huatec.iot.iotservice.pojo.dto.ExamQuestionOptionsDto;
import com.huatec.iot.iotservice.pojo.qry.ExamPaperPageQry;
import com.huatec.iot.iotservice.pojo.qry.ExamPaperQry;
import com.huatec.iot.iotservice.pojo.vo.ExamPapersVo;
import com.huatec.iot.iotservice.pojo.vo.ExamQuestionVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExamService {

    ExamPapersVo getExamPapers(ExamPaperQry qry);

    void insertPaper(ExamPapersDto examPapersDto);

    void updatePaper(ExamPapersDto examPapersDto);

    void deletePaperById(String id);

    void insertQuestion(ExamQuestionDto examQuestionDto);

    void updateQuestion(ExamQuestionDto examQuestionDto);

    void deleteQuestion(String paperId,String questionId);

    void insertQuestionOptions(ExamQuestionOptionsDto examQuestionDto);

    void updateQuestionOptions(ExamQuestionOptionsDto examQuestionDto);

    void deleteQuestionOptions(String id);

    void uploadQuestionAttachment(MultipartFile file, String questionId);

    void deleteQuestionAttachment(String id);

    List<ExamPapersVo> getExamPaperslist(ExamPaperPageQry examPaperPageQry);

    String uploadImg(MultipartFile file);

    String getActiveExamPapers();

    void ActiveExamPapers(String id);

    ExamQuestionVo getQuestionInfo(String id);
}
