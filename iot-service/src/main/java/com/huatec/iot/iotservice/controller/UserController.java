package com.huatec.iot.iotservice.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huatec.iot.common.pojo.BaseController;
import com.huatec.iot.common.response.Response;
import com.huatec.iot.common.response.ResponseHelper;
import com.huatec.iot.common.response.SingleResponse;
import com.huatec.iot.iotservice.pojo.dto.LoginDto;
import com.huatec.iot.iotservice.pojo.vo.UsersVo;
import com.huatec.iot.iotservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: huatec-iot-api
 * @description: 用户管理
 * @author: jiangtaohou
 * @create: 2023-04-13 09:44
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
@ApiSupport(order = 101, author = "侯江涛")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @ApiOperationSupport(order = 1)
    public Response login(@RequestBody @Validated LoginDto loginDto) {

        UsersVo usersVo = userService.login(loginDto);

        return SingleResponse.of(usersVo);

    }

    @ApiOperation(value = "退出登录")
    @GetMapping(value = "/logout")
    @ApiOperationSupport(order = 1)
    public Response logout(HttpServletRequest request) {
        userService.logout(request);

        return ResponseHelper.buildSuccess();
    }
}
