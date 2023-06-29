package com.huatec.iot.iotservice.controller;

import com.github.pagehelper.page.PageMethod;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.huatec.iot.common.response.PageResponse;
import com.huatec.iot.common.response.Response;
import com.huatec.iot.common.response.ResponseHelper;
import com.huatec.iot.common.response.SingleResponse;
import com.huatec.iot.common.utils.VUtils;
import com.huatec.iot.iotservice.pojo.dto.ExamPapersDto;
import com.huatec.iot.iotservice.pojo.dto.ExamQuestionDto;
import com.huatec.iot.iotservice.pojo.dto.ExamQuestionOptionsDto;
import com.huatec.iot.iotservice.pojo.qry.ExamPaperPageQry;
import com.huatec.iot.iotservice.pojo.qry.ExamPaperQry;
import com.huatec.iot.iotservice.pojo.vo.ExamPapersVo;
import com.huatec.iot.iotservice.pojo.vo.ExamQuestionVo;
import com.huatec.iot.iotservice.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/exam")
@Api(tags = "试题管理")
@Slf4j
@Validated
public class ExamController {
    static String PWD_SIGN = "977727dd0b083f246afed0f149b04b3e";
    @Autowired
    ExamService examService;


    @ApiOperation(value = "获取当前激活的试卷")
    @GetMapping("/paper/active/info")
    @ApiOperationSupport(order = 1)
    public SingleResponse<String> getActiveExamPapers(@ApiParam(name = "sign", value = "sign", required = true) @RequestParam String sign) {
        if (StringUtils.isBlank(sign) || !sign.equals(PWD_SIGN)) {
            VUtils.isTure(true).throwMessage("请登录");
        }
        String id = examService.getActiveExamPapers();

        return SingleResponse.of(id);
    }


    @ApiOperation(value = "激活试卷")
    @PostMapping("/paper/active")
    public Response ActiveExamPapers(@ApiParam(name = "id", value = "ID", required = true) @RequestParam String id) {
        log.info("【考试模块】修改-入参，id：{}", id);
        examService.ActiveExamPapers(id);
        return Response.buildSuccess();
    }


    @ApiOperation(value = "查询分页试卷列表")
    @GetMapping("/paper/list")
    @ApiOperationSupport(order = 1)
    public PageResponse<ExamPapersVo> getExamPaperslist(ExamPaperPageQry examPaperPageQry) {
        log.info("【考试模块】查询-入参，examPaperPageQry：{}", examPaperPageQry);
        PageMethod.startPage(examPaperPageQry.getPageIndex(), examPaperPageQry.getPageSize());

        List<ExamPapersVo> pageList = examService.getExamPaperslist(examPaperPageQry);

        return ResponseHelper.buildPage(pageList);
    }


    @ApiOperation(value = "查询试卷信息")
    @GetMapping("/paper/info")
    @ApiOperationSupport(order = 1)
    public SingleResponse<ExamPapersVo> getExamPapers(ExamPaperQry qry) {
        log.info("【考试模块】查询-入参，ExamPaperQry：{}", qry);
        ExamPapersVo papersVo = examService.getExamPapers(qry);

        return SingleResponse.of(papersVo);
    }

    @ApiOperation(value = "获取激活的试卷信息")
    @GetMapping("active/paperInfo")
    @ApiOperationSupport(order = 1)
    public SingleResponse<ExamPapersVo> getActivePaperInfo(
            @ApiParam(name = "id", value = "试卷ID", required = true) @RequestParam String id,
            @ApiParam(name = "type", value = "类型", required = true) @RequestParam String type,
            @ApiParam(name = "sign", value = "sign", required = true) @RequestParam String sign
    ) {
        if (StringUtils.isBlank(sign) || !sign.equals(PWD_SIGN)) {
            VUtils.isTure(true).throwMessage("请登录");
        }
        ExamPaperQry qry = new ExamPaperQry();
        qry.setId(id);
        qry.setType(type);
        log.info("【考试模块】查询-入参，ExamPaperQry：{}", qry);
        ExamPapersVo papersVo = examService.getExamPapers(qry);

        return SingleResponse.of(papersVo);
    }


    @ApiOperation(value = "添加试卷")
    @PostMapping("/paper/insert")
    @ApiOperationSupport(order = 3)
    public Response insertPaper(@RequestBody ExamPapersDto examPapersDto) {
        log.info("【考试模块】添加-入参，examPapersDto：{}", examPapersDto);
        examService.insertPaper(examPapersDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "修改试卷")
    @PostMapping("/paper/update")
    public Response updatePaper(@RequestBody ExamPapersDto examPapersDto) {
        log.info("【考试模块】修改-入参，examPapersDto：{}", examPapersDto);
        examService.updatePaper(examPapersDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "删除试卷")
    @PostMapping("/paper/delete")
    public Response deletePaper(@ApiParam(name = "id", value = "ID", required = true) @RequestParam String id) {
        log.info("【考试模块】删除-入参，id：{}", id);
        examService.deletePaperById(id);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "查看试题详情")
    @GetMapping("/question/info")
    @ApiOperationSupport(order = 1)
    public SingleResponse<ExamQuestionVo> getQuestionInfo(String id) {
        log.info("【考试模块】查询-入参，id：{}", id);
        ExamQuestionVo questionInfo = examService.getQuestionInfo(id);
        return SingleResponse.of(questionInfo);
    }

    @ApiOperation(value = "添加试题")
    @PostMapping("/question/insert")
    public Response insertQuestion(@RequestBody ExamQuestionDto examQuestionDto) {
        log.info("【考试模块】添加-入参，examPapersDto：{}", examQuestionDto);
        examService.insertQuestion(examQuestionDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "修改试题")
    @PostMapping("/question/update")
    public Response updateQuestion(@RequestBody ExamQuestionDto examQuestionDto) {
        log.info("【考试模块】修改-入参，examPapersDto：{}", examQuestionDto);
        examService.updateQuestion(examQuestionDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "删除试题")
    @PostMapping("/question/delete")
    public Response deleteQuestion(@ApiParam(name = "paperId", value = "paperId", required = true) @RequestParam String paperId,
                                   @ApiParam(name = "questionId", value = "questionId", required = true) @RequestParam String questionId) {
        log.info("【考试模块】删除-入参，id：{}", paperId);
        examService.deleteQuestion(paperId, questionId);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "添加选项")
    @PostMapping("/questionOptison/insert")
    public Response insertQuestionOptions(@RequestBody ExamQuestionOptionsDto examQuestionDto) {
        log.info("【考试模块】添加-入参，examPapersDto：{}", examQuestionDto);
        examService.insertQuestionOptions(examQuestionDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "修改选项")
    @PostMapping("/questionOptison/update")
    public Response updateQuestionOptions(@RequestBody ExamQuestionOptionsDto examQuestionDto) {
        log.info("【考试模块】修改-入参，examPapersDto：{}", examQuestionDto);
        examService.updateQuestionOptions(examQuestionDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "删除选项")
    @PostMapping("/questionOptison/delete")
    public Response deleteQuestionOptions(@ApiParam(name = "id", value = "ID", required = true) @RequestParam String id) {
        log.info("【考试模块】删除-入参，id：{}", id);
        examService.deleteQuestionOptions(id);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "上传附件", httpMethod = "POST")
    @PostMapping("/questionAttachment/upload")
    @ResponseBody
    public Response uploadQuestionAttachment(@RequestPart(name = "file") MultipartFile file,
                                             @ApiParam(name = "questionId", value = "试题ID", required = true) @RequestParam String questionId) throws Exception {
        examService.uploadQuestionAttachment(file, questionId);
        return ResponseHelper.buildSuccess();
    }

    @ApiOperation(value = "删除附件 ")
    @PostMapping("/questionAttachment/delete")
    @ApiOperationSupport(order = 5)
    public Response deleteQuestionAttachment(@ApiParam(name = "id", value = "ID", required = true) @RequestParam String id) {
        log.info("【考试模块】删除-入参，id：{}", id);
        examService.deleteQuestionAttachment(id);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "上传图片", httpMethod = "POST")
    @PostMapping("/img/upload")
    @ResponseBody
    public SingleResponse<String> uploadImg(@RequestPart(name = "file") MultipartFile file) throws Exception {
        String url = examService.uploadImg(file);
        return SingleResponse.of(url);
    }

    @ApiOperation(value = "批量添加试题")
    @PostMapping("/questionList/insert")
    public Response insertQuestionList(@RequestBody List<ExamQuestionDto> examQuestionDtoList) {
        log.info("【考试模块】添加-入参，examQuestionDtoList：{}", examQuestionDtoList);
        for (int i = 0; i < examQuestionDtoList.size(); i++) {
            ExamQuestionDto examQuestionDto = examQuestionDtoList.get(i);
            examService.insertQuestion(examQuestionDto);
        }

        return Response.buildSuccess();
    }


}
