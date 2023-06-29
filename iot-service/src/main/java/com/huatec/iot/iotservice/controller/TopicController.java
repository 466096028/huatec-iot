package com.huatec.iot.iotservice.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.pojo.BaseController;
import com.huatec.iot.common.response.MultiResponse;
import com.huatec.iot.iotservice.pojo.qry.TopicsQry;
import com.huatec.iot.iotservice.pojo.vo.CategorysVo;
import com.huatec.iot.iotservice.pojo.vo.TopicsVo;
import com.huatec.iot.iotservice.service.CategoryService;
import com.huatec.iot.iotservice.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 通信Topic管理
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/
@RestController
@RequestMapping("/topics")
@Api(tags = "通信Topic管理")
@ApiSupport(order = 101, author = "侯江涛")
@Slf4j
public class TopicController extends BaseController {

    @Autowired
    TopicService topicService;

    @ApiOperation(value = "查询所有通信Topic列表")
    @GetMapping("/allList")
    @ApiOperationSupport(order = 1)
    public MultiResponse<TopicsVo> allList(TopicsQry topicsQry) {
        log.info("【通信Topic模块】查询所有列表-入参，topicsQry：{}", topicsQry);
        List<TopicsVo> list = topicService.allList(topicsQry);

        return MultiResponse.of(list);
    }
}
