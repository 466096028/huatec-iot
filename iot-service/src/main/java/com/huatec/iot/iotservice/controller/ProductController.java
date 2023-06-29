package com.huatec.iot.iotservice.controller;

import com.github.pagehelper.page.PageMethod;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.response.*;
import com.huatec.iot.common.valid.ValidationGroups;
import com.huatec.iot.iotservice.pojo.dto.ProductsDto;
import com.huatec.iot.iotservice.pojo.qry.ProductsPageQry;
import com.huatec.iot.iotservice.pojo.vo.ProductDeviceCountVo;
import com.huatec.iot.iotservice.pojo.vo.ProductsVo;
import com.huatec.iot.iotservice.pojo.vo.ThingModelVo;
import com.huatec.iot.iotservice.service.ProductService;
import io.swagger.annotations.*;
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
@RequestMapping("/products")
@Api(tags = "产品管理")
@ApiSupport(order = 101, author = "侯江涛")
@Slf4j
@Validated
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation(value = "查询分页产品列表")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    public PageResponse<ProductsVo> list(ProductsPageQry productsPageQry) {
        log.info("【产品模块】查询-入参，productsQry：{}", productsPageQry);
        PageMethod.startPage(productsPageQry.getPageIndex(), productsPageQry.getPageSize());

        List<ProductsVo> pageList = productService.getPageList(productsPageQry);

        return ResponseHelper.buildPage(pageList);
    }

    @ApiOperation(value = "查询所有产品列表")
    @GetMapping("/allList")
    @ApiOperationSupport(order = 2)
    public MultiResponse<ProductsVo> allList() {
        log.info("【产品模块】查询所有列表-入参，{}", "");
        List<ProductsVo> list = productService.allList();

        return MultiResponse.of(list);
    }

    @ApiOperation(value = "查询产品详情")
    @GetMapping("/info")
    @ApiOperationSupport(order = 2)
    public SingleResponse<ProductsVo> info(
            @NotNull
            @Min(value = 1)
            @ApiParam(name = "id", value = "ID", required = true) @RequestParam Integer id) {
        log.info("【产品模块】查询详情-入参，id：{}", id);
        ProductsVo info = productService.getInfo(id);

        return SingleResponse.of(info);
    }

    @ApiOperation(value = "添加产品")
    @PostMapping("/insert")
    @ApiOperationSupport(order = 3)
    public Response insert(@RequestBody @Validated(ValidationGroups.Insert.class) ProductsDto productsDto) {
        log.info("【产品模块】添加-入参，productsDto：{}", productsDto);
        productService.insert(productsDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "修改产品")
    @PostMapping("/update")
    @ApiOperationSupport(order = 4)
    public Response update(@RequestBody @Validated(ValidationGroups.Update.class) ProductsDto productsDto) {
        log.info("【产品模块】修改-入参，productsDto：{}", productsDto);
        productService.update(productsDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "删除产品")
    @PostMapping("/delete")
    @ApiOperationSupport(order = 5)
    public Response delete(
            @NotNull
            @Min(value = 1)
            @ApiParam(name = "id", value = "ID", required = true) @RequestParam Integer id) {
        log.info("【产品模块】删除-入参，id：{}", id);
        productService.deleteById(id);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "查询产品关联设备数")
    @GetMapping("/deviceCount")
    @ApiOperationSupport(order = 7)
    public SingleResponse<ProductDeviceCountVo> deviceCount(
            @ApiParam(name = "productId", value = "产品ID", required = false)
            @RequestParam(required = false) Integer productId) {
        log.info("【产品模块】查询详情-入参，productId：{}", productId);
        ProductDeviceCountVo productDeviceCountVo = productService.getDeviceCount(productId);

        return SingleResponse.of(productDeviceCountVo);
    }

    @ApiOperation(value = "查询物模型TSL")
    @GetMapping("/thingModel")
    @ApiOperationSupport(order = 8)
    public SingleResponse<ThingModelVo> thingModel(
            @ApiParam(name = "id", value = "产品ID", required = true)
            @NotNull
            @Min(value = 1)
            @RequestParam Integer id,
            @ApiParam(name = "moduleId", value = "模型ID", required = false, example = "0")
            @RequestParam(required = false, defaultValue = "0") Integer moduleId) {
        log.info("【产品模块】查询物模型TSL-入参，id：{}，moduleId：{}", id, moduleId);
        ThingModelVo thingModelVo = productService.getThingModel(id, moduleId);

        return SingleResponse.of(thingModelVo);
    }
}
