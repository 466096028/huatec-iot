package com.huatec.iot.iotservice.service;

import com.huatec.iot.common.entity.Users;
import com.huatec.iot.iotservice.pojo.dto.LoginDto;
import com.huatec.iot.iotservice.pojo.vo.UsersVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
public interface UserService {

    UsersVo login(LoginDto loginDto);

    void logout(HttpServletRequest request);

    Users getInfo(Users users);
}
