package com.huatec.iot.iotservice.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.response.MultiResponse;
import com.huatec.iot.common.response.Response;
import com.huatec.iot.common.response.SingleResponse;
import com.huatec.iot.common.valid.ValidationGroups;
import com.huatec.iot.iotservice.pojo.dto.ProductModuleAbilitysDto;
import com.huatec.iot.iotservice.pojo.qry.ByteVParamsQry;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityDataQry;
import com.huatec.iot.iotservice.pojo.qry.ProductModuleAbilityQry;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilityDatasVo;
import com.huatec.iot.iotservice.pojo.vo.ProductModuleAbilitysVo;
import com.huatec.iot.iotservice.service.ByteVService;
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
import java.util.Map;

/**
 * @program: huatec-iot-api
 * @description: 产品管理
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/
@RestController
@RequestMapping("/bytev")
@Api(tags = "bytev数据对接管理")
@ApiSupport(order = 1001, author = "侯江涛")
@Slf4j
@Validated
public class ByteVController {

    @Autowired
    ByteVService byteVService;

    @ApiOperation(value = "指定功能的最新一条数据")
    @GetMapping("/abilitylatestData")
    @ApiOperationSupport(order = 1)
    public Map abilitylatestData(@Validated ByteVParamsQry byteVParamsQry) {
        log.info("【bytev数据对接模块】指定功能的最新一条数据-入参，byteVParamsQry：{}", byteVParamsQry);
        Map map = byteVService.abilitylatestData(byteVParamsQry);

        return map;
    }

    @ApiOperation(value = "设备下所有功能的最新数据列表")
    @GetMapping("/deviceLastestData")
    @ApiOperationSupport(order = 2)
    public List deviceLastestData(@Validated ByteVParamsQry byteVParamsQry) {
        log.info("【bytev数据对接模块】设备下所有功能的最新数据列表-入参，byteVParamsQry：{}", byteVParamsQry);
        List list = byteVService.deviceLastestData(byteVParamsQry);

        return list;
    }

    @ApiOperation(value = "指定多设备多功能的最新20条数据")
    @GetMapping("/multifunctionalLastestTwentyData")
    @ApiOperationSupport(order = 3)
    public List multifunctionalLastestTwentyData(@Validated ByteVParamsQry byteVParamsQry) {
        log.info("【bytev数据对接模块】指定多设备多功能的最新20条数据-入参，byteVParamsQry：{}", byteVParamsQry);
        List list = byteVService.multifunctionalLastestTwentyData(byteVParamsQry);

        return list;
    }
}
