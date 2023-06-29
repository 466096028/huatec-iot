package com.huatec.iot.iotservice.controller;

import com.github.pagehelper.page.PageMethod;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.annotation.EnumAnnotation;
import com.huatec.iot.common.enums.StatusEnums;
import com.huatec.iot.common.response.*;
import com.huatec.iot.common.valid.ValidationGroups;
import com.huatec.iot.iotservice.pojo.dto.DevicesDto;
import com.huatec.iot.iotservice.pojo.qry.DevicesPageQry;
import com.huatec.iot.iotservice.pojo.qry.DevicesQry;
import com.huatec.iot.iotservice.pojo.vo.*;
import com.huatec.iot.iotservice.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * @program: huatec-iot-api
 * @description: 设备管理
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/
@RestController
@RequestMapping("/devices")
@Api(tags = "设备管理")
@ApiSupport(order = 101, author = "侯江涛")
@Slf4j
@Validated
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @ApiOperation(value = "查询分页设备列表")
    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    public PageResponse<DevicesProductsVo> list(DevicesPageQry devicesPageQry) {
        log.info("【设备模块】查询-入参，devicesPageQry：{}", devicesPageQry);
        PageMethod.startPage(devicesPageQry.getPageIndex(), devicesPageQry.getPageSize());

        List<DevicesProductsVo> pageList = deviceService.getPageList(devicesPageQry);

        return ResponseHelper.buildPage(pageList);
    }

    @ApiOperation(value = "查询所有设备列表")
    @GetMapping("/allList")
    @ApiOperationSupport(order = 2)
    public MultiResponse<DevicesVo> allList(DevicesQry devicesQry) {
        log.info("【设备模块】查询所有列表-入参，devicesQry：{}", devicesQry);
        List<DevicesVo> list = deviceService.allList(devicesQry);

        return MultiResponse.of(list);
    }


    @ApiOperation(value = "查询设备详情")
    @GetMapping("/info")
    @ApiOperationSupport(order = 2)
    public SingleResponse<DevicesVo> info(@ApiParam(name = "id", value = "ID", required = true) @RequestParam Integer id) {
        log.info("【设备模块】查询详情-入参，id：{}", id);
        DevicesVo info = deviceService.getInfo(id);

        return SingleResponse.of(info);
    }

    @ApiOperation(value = "添加设备")
    @PostMapping("/insert")
    @ApiOperationSupport(order = 3)
    public Response insert(@RequestBody @Validated(ValidationGroups.Insert.class) DevicesDto devicesDto) {
        log.info("【设备模块】添加-入参，devicesDto：{}", devicesDto);
        deviceService.insert(devicesDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "修改设备")
    @PostMapping("/update")
    @ApiOperationSupport(order = 4)
    public Response update(@RequestBody @Validated(ValidationGroups.Update.class) DevicesDto devicesDto) {
        log.info("【设备模块】修改-入参，devicesDto：{}", devicesDto);
        deviceService.update(devicesDto);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "删除设备")
    @PostMapping("/delete")
    @ApiOperationSupport(order = 5)
    public Response delete(
            @NotNull
            @Min(value = 1)
            @ApiParam(name = "id", value = "ID", required = true) @RequestParam Integer id) {
        log.info("【设备模块】删除-入参，id：{}", id);
        deviceService.deleteById(id);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "修改状态")
    @PostMapping("/updateStatus")
    @ApiOperationSupport(order = 6)
    public Response updateStatus(
            @NotNull
            @Min(value = 1)
            @ApiParam(name = "id", value = "ID", required = true) @RequestParam Integer id,
            @EnumAnnotation(message = "状态必须为指定值", target = StatusEnums.class)
            @ApiParam(name = "status", value = "状态:0为启用，1为禁用", required = true)
            @RequestParam Byte status) {
        log.info("【设备模块】修改设备状态-入参，id：{}, status：{}", id, status);
        deviceService.updateStatus(id, status);

        return Response.buildSuccess();
    }

    @ApiOperation(value = "MQTT连接参数")
    @GetMapping("/connectParams")
    @ApiOperationSupport(order = 7)
    public SingleResponse<ConnectParamsVo> connectParams(
            @NotNull
            @Min(value = 1)
            @ApiParam(name = "id", value = "ID", required = true) @RequestParam Integer id) {
        log.info("【设备模块】MQTT连接参数-入参，id：{}", id);

        ConnectParamsVo connectParamsVo = deviceService.connectParams(id);

        return SingleResponse.of(connectParamsVo);
    }

    @ApiOperation(value = "系统MQTT连接信息")
    @GetMapping("/mqttConnectInfo")
    @ApiOperationSupport(order = 8)
    public SingleResponse mqttConnectInfo(
            @NotBlank
            @Size(min = 1, message = "用户名不能为空")
            @ApiParam(name = "", value = "用户名", required = true) @RequestParam String userName
    ) {
        log.info("【设备模块】系统MQTT连接信息-入参，userName：{}", userName);

        Map<String, MqttConnectInfoVo> map = deviceService.mqttConnectInfo(userName);
        return SingleResponse.of(map);
    }
}
