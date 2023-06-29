package com.huatec.iot.iotservice.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.pojo.BaseController;
import com.huatec.iot.common.response.MultiResponse;
import com.huatec.iot.iotservice.pojo.vo.CategorysVo;
import com.huatec.iot.iotservice.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 分类管理
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/
@RestController
@RequestMapping("/categorys")
@Api(tags = "分类管理")
@ApiSupport(order = 101, author = "侯江涛")
@Slf4j
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;

    // @ApiOperation(value = "查询分页分类列表")
    // @GetMapping("/list")
    // @ApiOperationSupport(order = 1)
    // public PageResponse<CategorysVo> list(CategorysQry categorysQry) {
    //     log.info("【分类模块】查询分页入参：{}", categorysQry);
    //     PageMethod.startPage(categorysQry.getPageIndex(), categorysQry.getPageSize());
    //
    //     List<CategorysVo> pageList = categoryService.getPageList(categorysQry);
    //
    //     return ResponseHelper.buildPage(pageList);
    // }

    @ApiOperation(value = "查询所有分类列表")
    @GetMapping("/allList")
    @ApiOperationSupport(order = 1)
    public MultiResponse<CategorysVo> allList() {
        log.info("【分类模块】查询所有列表-入参，{}", "");
        List<CategorysVo> list = categoryService.allList();

        return MultiResponse.of(list);
    }
}
