package com.huatec.iot.iotservice.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.domain.mqtt.MqttServiceClient;
import com.huatec.iot.common.enums.NodeTypeEnums;
import com.huatec.iot.common.pojo.BaseController;
import com.huatec.iot.common.response.Response;
import com.huatec.iot.common.response.SingleResponse;
import com.huatec.iot.common.utils.UserUtils;
import com.huatec.iot.iotservice.pojo.vo.ProductEnumsVo;
import com.huatec.iot.iotservice.pojo.vo.ThingModelVo;
import com.huatec.iot.iotservice.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: huatec-iot-api
 * @description: 公共管理
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/
@RestController
@RequestMapping("/common")
@Api(tags = "公共管理")
@ApiSupport(order = 101, author = "侯江涛")
@Slf4j
public class CommonController extends BaseController {

    @Autowired
    CommonService commonService;

    @Autowired
    MqttServiceClient mqttServiceClient;

    @ApiOperation(value = "获取产品枚举")
    @GetMapping("/productEnums")
    @ApiOperationSupport(order = 1)
    public SingleResponse<ProductEnumsVo> productEnums() {
        log.info("【公共模块】查询产品枚举-入参，{}", "");
        ProductEnumsVo info = commonService.productEnums();

        return SingleResponse.of(info);
    }

    @ApiOperation(value = "初始化数据")
    @GetMapping("/initData")
    @ApiOperationSupport(order = 2)
    public Response initData() {
        log.info("【公共模块】初始化数据-入参，userId：{}", UserUtils.getUserInfo().getUserId());
        commonService.initData(0);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "初始化mqtt连接", hidden = true)
    @GetMapping("/initMqttConnect")
    public void initMqttConnect(@RequestParam Integer event) {
        mqttServiceClient.init();
        if (event!= null && event >0) {
            mqttServiceClient.subscribeAllEvent();
        }
    }
}
