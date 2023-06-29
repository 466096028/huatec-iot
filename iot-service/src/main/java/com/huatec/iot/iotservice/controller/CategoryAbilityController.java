package com.huatec.iot.iotservice.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.pojo.BaseController;
import com.huatec.iot.common.response.MultiResponse;
import com.huatec.iot.iotservice.pojo.qry.CategoryAbilitysQry;
import com.huatec.iot.iotservice.pojo.vo.CategoryAbilitysVo;
import com.huatec.iot.iotservice.service.CategoryAbilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 分类功能管理
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/
@RestController
@RequestMapping("/categoryAbility")
@Api(tags = "分类功能管理")
@ApiSupport(order = 101, author = "侯江涛")
@Slf4j
public class CategoryAbilityController extends BaseController {

    @Autowired
    CategoryAbilityService categoryAbilityService;

    @ApiOperation(value = "查询所有分类功能列表")
    @GetMapping("/allList")
    @ApiOperationSupport(order = 1)
    public MultiResponse<CategoryAbilitysVo> allList(CategoryAbilitysQry categoryAbilitysQry) {
        log.info("【分类功能模块】查询所有列表-入参，categoryAbilitysQry：{}", categoryAbilitysQry);
        List<CategoryAbilitysVo> list = categoryAbilityService.allList(categoryAbilitysQry);

        return MultiResponse.of(list);
    }
}
