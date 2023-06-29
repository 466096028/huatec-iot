package com.huatec.iot.iotservice.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.response.*;
import com.huatec.iot.common.valid.ValidationGroups;
import com.huatec.iot.iotservice.pojo.dto.ProductModuleAbilitysDto;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityDataQry;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityQry;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilityDatasVo;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilitysVo;
import com.huatec.iot.iotservice.service.ProductModuleAbilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 产品管理
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/
@RestController
@RequestMapping("/productModuleAbility")
@Api(tags = "产品模型功能管理")
@ApiSupport(order = 101, author = "侯江涛")
@Slf4j
@Validated
public class ProductModuleAbilityController {

    @Autowired
    ProductModuleAbilityService productModuleAbilityService;


    @ApiOperation(value = "查询用户产品模型功能列表")
    @GetMapping("/userModuleList")
    @ApiOperationSupport(order = 1)
    public MultiResponse<ProductModuleAbilitysVo> userModuleList(@Validated ProductModuleAbilityQry productModuleAbilityQry) {
        log.info("【产品模型功能模块】查询用户产品模型功能列表-入参，productModuleAbilityQry：{}", productModuleAbilityQry);
        List<ProductModuleAbilitysVo> list = productModuleAbilityService.userModuleList(productModuleAbilityQry);

        return MultiResponse.of(list);
    }

    @ApiOperation(value = "查询模型功能数据列表")
    @GetMapping("/dataList")
    @ApiOperationSupport(order = 2)
    public MultiResponse<ProductModuleAbilityDatasVo> list(@Validated ProductModuleAbilityDataQry productModuleAbilityDataQry) {
        log.info("【产品模型功能模块】查询模型功能数据列表-入参，productModuleAbilityDataPageQry：{}", productModuleAbilityDataQry);

        List<ProductModuleAbilityDatasVo> list = productModuleAbilityService.getDataList(productModuleAbilityDataQry);

        return MultiResponse.of(list);
    }


    @ApiOperation(value = "查询产品模型功能详情")
    @GetMapping("/info")
    @ApiOperationSupport(order = 3)
    public SingleResponse<ProductModuleAbilitysVo> info(
            @NotNull
            @Min(value = 1)
            @ApiParam(name = "id", value = "ID", required = true) @RequestParam Integer id) {
        log.info("【产品模型功能模块】查询详情-入参，id：{}", id);
        ProductModuleAbilitysVo info = productModuleAbilityService.getInfo(id);

        return SingleResponse.of(info);
    }

    @ApiOperation(value = "添加产品模型功能")
    @PostMapping("/insert")
    @ApiOperationSupport(order = 3)
    public Response insert(@RequestBody @Validated(ValidationGroups.Insert.class) ProductModuleAbilitysDto productModuleAbilitysDto) {
        log.info("【产品模型功能模块】添加-入参，productModuleAbilitysDto：{}", productModuleAbilitysDto);
        productModuleAbilityService.insert(productModuleAbilitysDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "修改产品模型功能")
    @PostMapping("/update")
    @ApiOperationSupport(order = 4)
    public Response update(@RequestBody @Validated(ValidationGroups.Update.class) ProductModuleAbilitysDto productModuleAbilitysDto) {
        log.info("【产品模型功能模块】添加-入参，productModuleAbilitysDto：{}", productModuleAbilitysDto);
        productModuleAbilityService.update(productModuleAbilitysDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "删除产品模型功能")
    @PostMapping("/delete")
    @ApiOperationSupport(order = 5)
    public Response delete(
            @NotNull
            @Min(value = 1)
            @ApiParam(name = "id", value = "ID", required = true) @RequestParam Integer id) {
        log.info("【产品模型功能模块】删除-入参，id：{}", id);
        productModuleAbilityService.deleteById(id);

        return Response.buildSuccess();
    }
}
