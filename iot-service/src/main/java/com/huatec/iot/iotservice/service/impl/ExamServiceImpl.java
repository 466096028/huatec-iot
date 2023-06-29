package com.huatec.iot.iotservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.huatec.iot.common.entity.*;
import com.huatec.iot.common.utils.UserUtils;
import com.huatec.iot.common.utils.UuidUtils;
import com.huatec.iot.iotservice.mapper.*;
import com.huatec.iot.iotservice.pojo.dto.ExamPapersDto;
import com.huatec.iot.iotservice.pojo.dto.ExamQuestionDto;
import com.huatec.iot.iotservice.pojo.dto.ExamQuestionOptionsDto;
import com.huatec.iot.iotservice.pojo.qry.ExamPaperPageQry;
import com.huatec.iot.iotservice.pojo.qry.ExamPaperQry;
import com.huatec.iot.iotservice.pojo.vo.*;
import com.huatec.iot.iotservice.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamPapersMapper examPapersMapper;
    @Autowired
    ExamQuestionAttachmentMapper examQuestionAttachmentMapper;
    @Autowired
    ExamQuestionMapper examQuestionMapper;
    @Autowired
    ExamQuestionOptionsMapper examQuestionOptionsMapper;
    @Autowired
    ExamQuestionPaperMapper examQuestionPaperMapper;

    @Value("${upload.file.name}")
    String uploadFileName;

    @Value("${upload.file.url.prefix}")
    String uploadUrlPrefix;


    @Override
    public List<ExamPapersVo> getExamPaperslist(ExamPaperPageQry examPaperPageQry) {
        Example example = new Example(ExamPapers.class);
        example.setOrderByClause("id desc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        if (StringUtils.isNotBlank(examPaperPageQry.getId())) {
            criteria.andEqualTo("id", examPaperPageQry.getId());
        }
        if (StringUtils.isNotBlank(examPaperPageQry.getName())) {
            criteria.andLike("name", "%" + examPaperPageQry.getName() + "%");
        }

        List<ExamPapers> select = examPapersMapper.selectByExample(example);
        log.info("【考试模块】分页查询数据库-参数，params：{}，result：{}", JSON.toJSONString(criteria), select);


        List<ExamPapersVo> list = new ArrayList<>();

        select.forEach(stu -> {
            ExamPapersVo vO = new ExamPapersVo();
            BeanUtils.copyProperties(stu, vO);
            list.add(vO);
        });


        log.info("【考试模块】分页查询-结果，result：{}", list);

        return list;
    }


    @Override
    public ExamPapersVo getExamPapers(ExamPaperQry qry) {
        //查询相关问卷
        String id = qry.getId();
        String qstype = qry.getType();
        ExamPapers examPapers = examPapersMapper.getExamPapers(id);
        ExamPapersVo examPapersVo = new ExamPapersVo();
        BeanUtils.copyProperties(examPapers, examPapersVo);
        //查询关联试题
        //根据问卷id查询相关试题
        Map<String, Object> paM = new HashMap<>();
        paM.put("id", id);
        paM.put("type", qstype);
        List<ExamQuestion> examQuestionList = examPapersMapper.getExamQuestionListByPapersId(paM);
        List<ExamQuestionVo> examQuestionVoList = new ArrayList<>();
        examPapersVo.setExamQuestionVoList(examQuestionVoList);
        //查询试题关联选项
        for (int i = 0; i < examQuestionList.size(); i++) {
            ExamQuestionVo examQuestionVo = new ExamQuestionVo();
            ExamQuestion examQuestion = examQuestionList.get(i);
            BeanUtils.copyProperties(examQuestion, examQuestionVo);
            examQuestionVoList.add(examQuestionVo);
            String questionId = examQuestion.getQuestionId();
            //查询附件
            List<ExamQuestionAttachmentVo> attachmentVoList = new ArrayList<>();
            List<ExamQuestionAttachment> attachmentList = examQuestionAttachmentMapper.getAttachmentListByQuestionId(questionId);
            attachmentList.forEach(stu -> {
                ExamQuestionAttachmentVo vO = new ExamQuestionAttachmentVo();
                BeanUtils.copyProperties(stu, vO);
                attachmentVoList.add(vO);
            });
            BeanUtils.copyProperties(attachmentList, attachmentVoList);
            examQuestionVo.setExamQuestionAttachmentVoList(attachmentVoList);
            Short type = examQuestion.getType();
            //如果是选择题则查询选项
            if (type == 0) {
                List<ExamQuestionOptionsVo> examQuestionOptionsVoList = new ArrayList<>();
                List<ExamQuestionOptions> questionOptionsList = examQuestionOptionsMapper.getOptionsByQuestionId(questionId);
                BeanUtils.copyProperties(questionOptionsList, examQuestionOptionsVoList);
                questionOptionsList.forEach(stu -> {
                    ExamQuestionOptionsVo vO = new ExamQuestionOptionsVo();
                    BeanUtils.copyProperties(stu, vO);
                    examQuestionOptionsVoList.add(vO);
                });
                examQuestionVo.setExamQuestionOptionsVoList(examQuestionOptionsVoList);
            }
        }
        return examPapersVo;
    }

    @Override
    public void insertPaper(ExamPapersDto examPapersDto) {


        ExamPapers examPapers = new ExamPapers();
        BeanUtils.copyProperties(examPapersDto, examPapers);

        if (StringUtils.isBlank(examPapersDto.getId())) {
            examPapers.setId(UuidUtils.generateDeviceId());
        }
        if (examPapers.getCreateUserCode() == null) {
            examPapers.setCreateUserCode(UserUtils.getUserInfo().getUserId().toString());
            examPapers.setCreateUser(UserUtils.getUserInfo().getUserName());
        }
        examPapers.setCreateTime(new Date());
        log.info("【考试模块】添加数据-参数：examPapers：{}", examPapers);

        int insert = 0;
        try {
            insert = examPapersMapper.insertSelective(examPapers);
            log.info("【考试模块】添加数据-结果：result：{}", insert);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updatePaper(ExamPapersDto examPapersDto) {

        Example example = new Example(ExamPapers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", examPapersDto.getId());
        log.info("【考试模块】修改数据-参数，params：{}", JSON.toJSONString(criteria));

        ExamPapers examPapers = new ExamPapers();
        BeanUtils.copyProperties(examPapersDto, examPapers);
        examPapers.setUpdateUserCode(UserUtils.getUserInfo().getUserId().toString());
        examPapers.setUpdateUser(UserUtils.getUserInfo().getUserName());
        examPapers.setUpdateTime(new Date());
        int update = 0;
        try {
            update = examPapersMapper.updateByExampleSelective(examPapers, example);
            log.info("【考试模块】修改数据-结果，result：{}", update);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletePaperById(String id) {

        Example example = new Example(ExamPapers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        log.info("【考试模块】修改数据-参数，params：{}", JSON.toJSONString(criteria));


        ExamPapers examPapers = new ExamPapers();
        examPapers.setIsDel((short) 1);
        examPapers.setUpdateUserCode(UserUtils.getUserInfo().getUserId().toString());
        examPapers.setUpdateUser(UserUtils.getUserInfo().getUserName());
        examPapers.setUpdateTime(new Date());
        int update = 0;
        try {
            update = examPapersMapper.updateByExampleSelective(examPapers, example);
            log.info("【考试模块】修改数据-结果，result：{}", update);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertQuestion(ExamQuestionDto examQuestionDto) {
        ExamQuestion examQuestion = new ExamQuestion();
        BeanUtils.copyProperties(examQuestionDto, examQuestion);

        if (StringUtils.isBlank(examQuestionDto.getQuestionId())) {
            examQuestion.setQuestionId(UuidUtils.generateDeviceId());
        }
        if (examQuestion.getCreateUserCode() == null) {
            examQuestion.setCreateUserCode(UserUtils.getUserInfo().getUserId().toString());
            examQuestion.setCreateUser(UserUtils.getUserInfo().getUserName());
        }
        examQuestion.setCreateTime(new Date());


        ExamQuestionPaper examQuestionPaper = new ExamQuestionPaper();
        examQuestionPaper.setId(UuidUtils.generateDeviceId());
        examQuestionPaper.setPaperId(examQuestionDto.getPaperId());
        examQuestionPaper.setQuestionId(examQuestion.getQuestionId());
        examQuestionPaper.setSort(examQuestionDto.getSort());
        log.info("【考试模块】添加数据-参数：examQuestion：{}", examQuestion);

        int insert = 0;
        try {
            insert = examQuestionMapper.insertSelective(examQuestion);
            log.info("【考试模块】添加数据-结果：result：{}", insert);
            insert = examQuestionPaperMapper.insertSelective(examQuestionPaper);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //添加选项
        List<ExamQuestionOptionsDto> list = examQuestionDto.getExamQuestionOptionsDtoList();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ExamQuestionOptionsDto examQuestionOptionsDto = list.get(i);
                examQuestionOptionsDto.setQuestionId(examQuestionPaper.getQuestionId());
                examQuestionOptionsDto.setSort(i);
                insertQuestionOptions(examQuestionOptionsDto);
            }
        }

        //添加附件
        List<String> imgArr = examQuestionDto.getImgUlrList();
        if (imgArr != null && imgArr.size() > 0) {
            for (int i = 0; i < imgArr.size(); i++) {
                String imgUrl = imgArr.get(i);
                addQuestionAttachment(imgUrl, examQuestionPaper.getQuestionId());
            }
        }

    }

    @Override
    public void updateQuestion(ExamQuestionDto examQuestionDto) {
        Example example = new Example(ExamQuestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("questionId", examQuestionDto.getQuestionId());
        log.info("【考试模块】修改数据-参数，params：{}", JSON.toJSONString(criteria));

        ExamQuestion examQuestion = new ExamQuestion();
        BeanUtils.copyProperties(examQuestionDto, examQuestion);
        examQuestion.setUpdateUserCode(UserUtils.getUserInfo().getUserId().toString());
        examQuestion.setUpdateUser(UserUtils.getUserInfo().getUserName());
        examQuestion.setUpdateTime(new Date());
        int update = 0;
        try {
            update = examQuestionMapper.updateByExampleSelective(examQuestion, example);
            log.info("【考试模块】修改数据-结果，result：{}", update);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //删除选项
        delQuestionOptions(examQuestion.getQuestionId());
        //添加选项
        List<ExamQuestionOptionsDto> list = examQuestionDto.getExamQuestionOptionsDtoList();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ExamQuestionOptionsDto examQuestionOptionsDto = list.get(i);
                examQuestionOptionsDto.setQuestionId(examQuestion.getQuestionId());
                examQuestionOptionsDto.setSort(i);
                insertQuestionOptions(examQuestionOptionsDto);
            }
        }
        //删除附件
        delQuestionAttachment(examQuestion.getQuestionId());
        //添加附件
        List<String> imgArr = examQuestionDto.getImgUlrList();
        if (imgArr != null && imgArr.size() > 0) {
            for (int i = 0; i < imgArr.size(); i++) {
                String imgUrl = imgArr.get(i);
                addQuestionAttachment(imgUrl, examQuestion.getQuestionId());
            }
        }
    }

    @Override
    public void deleteQuestion(String paperId, String questionId) {

        Example example = new Example(ExamQuestionPaper.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("paperId", paperId);
        criteria.andEqualTo("questionId", questionId);

        int i = examQuestionPaperMapper.deleteByExample(example);
        log.info("【考试模块】删除数据，params：{}，result：{}", JSON.toJSONString(criteria), i);


    }

    private void delQuestionAttachment(String questionId) {
        Example example = new Example(ExamQuestionAttachment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("questionId", questionId);

        int i = examQuestionAttachmentMapper.deleteByExample(example);
        log.info("【考试模块】删除数据，params：{}，result：{}", JSON.toJSONString(criteria), i);

    }

    private void delQuestionOptions(String questionId) {
        Example example = new Example(ExamQuestionPaper.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("questionId", questionId);

        int i = examQuestionOptionsMapper.deleteByExample(example);
        log.info("【考试模块】删除数据，params：{}，result：{}", JSON.toJSONString(criteria), i);

    }



    @Override
    public void insertQuestionOptions(ExamQuestionOptionsDto examQuestionDto) {
        ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
        BeanUtils.copyProperties(examQuestionDto, examQuestionOptions);

        if (StringUtils.isBlank(examQuestionDto.getId())) {
            examQuestionOptions.setId(UuidUtils.generateDeviceId());
        }
        log.info("【考试模块】添加数据-参数：examQuestionOptions：{}", examQuestionOptions);

        int insert = 0;
        try {
            insert = examQuestionOptionsMapper.insertSelective(examQuestionOptions);
            log.info("【考试模块】添加数据-结果：result：{}", insert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestionOptions(ExamQuestionOptionsDto examQuestionDto) {
        Example example = new Example(ExamQuestionOptions.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", examQuestionDto.getId());
        log.info("【考试模块】修改数据-参数，params：{}", JSON.toJSONString(criteria));


        ExamQuestionOptions examQuestionOptions = new ExamQuestionOptions();
        BeanUtils.copyProperties(examQuestionDto, examQuestionOptions);
        int update = 0;
        try {
            update = examQuestionOptionsMapper.updateByExampleSelective(examQuestionOptions, example);
            log.info("【考试模块】修改数据-结果，result：{}", update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuestionOptions(String id) {
        Example example = new Example(ExamQuestionOptions.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        int i = examQuestionOptionsMapper.deleteByExample(example);
        log.info("【考试模块】删除数据，params：{}，result：{}", JSON.toJSONString(criteria), i);
        ;

    }

    @Override
    public void uploadQuestionAttachment(MultipartFile file, String questionId) {
        String path = uploadFileName;
        String urlPrefix = uploadUrlPrefix;
        String url = "";

        String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        try {
            url = uploadFile(file, path, urlPrefix, fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(url);

        ExamQuestionAttachment examQuestionAttachment = new ExamQuestionAttachment();
        examQuestionAttachment.setId(UuidUtils.generateDeviceId());
        examQuestionAttachment.setQuestionId(questionId);
        examQuestionAttachment.setFileName(fileName);
        examQuestionAttachment.setFileUrl(url);

        examQuestionAttachment.setCreateUserCode(UserUtils.getUserInfo().getUserId().toString());
        examQuestionAttachment.setCreateUser(UserUtils.getUserInfo().getUserName());
        examQuestionAttachment.setCreateTime(new Date());

        log.info("【考试模块】添加数据-参数：questionId：{}", questionId);

        int insert = 0;
        try {
            insert = examQuestionAttachmentMapper.insertSelective(examQuestionAttachment);
            log.info("【考试模块】添加数据-结果：result：{}", insert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteQuestionAttachment(String id) {

        Example example = new Example(ExamQuestionAttachment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        log.info("【考试模块】修改数据-参数，params：{}", JSON.toJSONString(criteria));


        ExamQuestionAttachment examQuestionAttachment = new ExamQuestionAttachment();
        examQuestionAttachment.setIsDel((short) 1);
        examQuestionAttachment.setUpdateUserCode(UserUtils.getUserInfo().getUserId().toString());
        examQuestionAttachment.setUpdateUser(UserUtils.getUserInfo().getUserName());
        examQuestionAttachment.setUpdateTime(new Date());
        int update = 0;
        try {
            update = examQuestionAttachmentMapper.updateByExampleSelective(examQuestionAttachment, example);
            log.info("【考试模块】修改数据-结果，result：{}", update);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String uploadImg(MultipartFile file) {
        String path = uploadFileName;
        String urlPrefix = uploadUrlPrefix;
        String url = "";
        String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        try {
            url = uploadFile(file, path, urlPrefix, fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(url);
        return url;
    }

    @Override
    public String getActiveExamPapers() {
        Example example = new Example(ExamPapers.class);
        example.setOrderByClause("id desc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("isCorrect", 1);

        ExamPapers select = examPapersMapper.selectOneByExample(example);
        log.info("【考试模块】分页查询数据库-参数，params：{}，result：{}", JSON.toJSONString(criteria), select);
        String id = select.getId();
        return id;
    }

    @Override
    public void ActiveExamPapers(String id) {

        activePapersRSBiz();
        activePapersBiz(id);

    }

    @Override
    public ExamQuestionVo getQuestionInfo(String id) {

        Example example = new Example(ExamQuestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("questionId", id);

        ExamQuestionVo examQuestionVo = new ExamQuestionVo();
        ExamQuestion examQuestion = examQuestionMapper.selectOneByExample(example);
        BeanUtils.copyProperties(examQuestion, examQuestionVo);
        String questionId = examQuestion.getQuestionId();
        //查询附件
        List<ExamQuestionAttachmentVo> attachmentVoList = new ArrayList<>();
        List<ExamQuestionAttachment> attachmentList = examQuestionAttachmentMapper.getAttachmentListByQuestionId(questionId);
        attachmentList.forEach(stu -> {
            ExamQuestionAttachmentVo vO = new ExamQuestionAttachmentVo();
            BeanUtils.copyProperties(stu, vO);
            attachmentVoList.add(vO);
        });
        BeanUtils.copyProperties(attachmentList, attachmentVoList);
        examQuestionVo.setExamQuestionAttachmentVoList(attachmentVoList);
        Short type = examQuestion.getType();
        //如果是选择题则查询选项
        if (type == 0) {
            List<ExamQuestionOptionsVo> examQuestionOptionsVoList = new ArrayList<>();
            List<ExamQuestionOptions> questionOptionsList = examQuestionOptionsMapper.getOptionsByQuestionId(questionId);
            BeanUtils.copyProperties(questionOptionsList, examQuestionOptionsVoList);
            questionOptionsList.forEach(stu -> {
                ExamQuestionOptionsVo vO = new ExamQuestionOptionsVo();
                BeanUtils.copyProperties(stu, vO);
                examQuestionOptionsVoList.add(vO);
            });
            examQuestionVo.setExamQuestionOptionsVoList(examQuestionOptionsVoList);
        }
        return examQuestionVo;
    }

    //重置激活状态位
    private void activePapersRSBiz() {
        Example example = new Example(ExamPapers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isCorrect", 1);
        log.info("【考试模块】激活-参数，params：{}", JSON.toJSONString(criteria));

        ExamPapers examPapers = new ExamPapers();
        examPapers.setIsCorrect(0);
        examPapers.setUpdateUserCode(UserUtils.getUserInfo().getUserId().toString());
        examPapers.setUpdateUser(UserUtils.getUserInfo().getUserName());
        examPapers.setUpdateTime(new Date());
        int update = 0;
        try {
            update = examPapersMapper.updateByExampleSelective(examPapers, example);
            log.info("【考试模块】激活-结果，result：{}", update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //激活指定问卷
    private void activePapersBiz(String id) {
        Example example = new Example(ExamPapers.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        log.info("【考试模块】激活-参数，params：{}", JSON.toJSONString(criteria));

        ExamPapers examPapers = new ExamPapers();
        examPapers.setIsCorrect(1);
        examPapers.setUpdateUserCode(UserUtils.getUserInfo().getUserId().toString());
        examPapers.setUpdateUser(UserUtils.getUserInfo().getUserName());
        examPapers.setUpdateTime(new Date());
        int update = 0;
        try {
            update = examPapersMapper.updateByExampleSelective(examPapers, example);
            log.info("【考试模块】激活-结果，result：{}", update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String uploadFile(MultipartFile upload, String path, String urlPrefix, String fileName) throws IOException {
        //判断该路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            //如果这个文件夹不存在的话,就创建这个文件
            file.mkdirs();
        }
        //获取上传文件名称
        // String filename = upload.getOriginalFilename();
        // System.out.println(filename);
        //把文件名称设置成唯一值 uuid 以防止文件名相同覆盖
        // String uuid = UUID.randomUUID().toString().replace("-", "");
        //新文件名git sss
        //完成文件上传

        upload.transferTo(new File(path + fileName));
        String filePath = urlPrefix + fileName;
        return filePath;
    }

    private void addQuestionAttachment(String url, String questionId) {
        ExamQuestionAttachment examQuestionAttachment = new ExamQuestionAttachment();
        examQuestionAttachment.setId(UuidUtils.generateDeviceId());
        examQuestionAttachment.setQuestionId(questionId);
        examQuestionAttachment.setFileUrl(url);
        examQuestionAttachment.setCreateUserCode(UserUtils.getUserInfo().getUserId().toString());
        examQuestionAttachment.setCreateUser(UserUtils.getUserInfo().getUserName());
        examQuestionAttachment.setCreateTime(new Date());

        log.info("【考试模块】添加数据-参数：questionId：{}", questionId);

        int insert = 0;
        try {
            insert = examQuestionAttachmentMapper.insertSelective(examQuestionAttachment);
            log.info("【考试模块】添加数据-结果：result：{}", insert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
