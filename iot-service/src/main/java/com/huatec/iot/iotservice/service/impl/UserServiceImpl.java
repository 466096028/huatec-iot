package com.huatec.iot.iotservice.service.impl;

import com.huatec.iot.common.config.RedisConfig;
import com.huatec.iot.common.constants.LoginConstants;
import com.huatec.iot.common.entity.Users;
import com.huatec.iot.common.enums.ResultCodeEnums;
import com.huatec.iot.common.utils.*;
import com.huatec.iot.iotservice.mapper.UsersMapper;
import com.huatec.iot.iotservice.pojo.dto.LoginDto;
import com.huatec.iot.iotservice.pojo.vo.UsersVo;
import com.huatec.iot.iotservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-13 10:31
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UsersMapper usersMapper;

    @Autowired
    RedisConfig redisConfig;

    @Override
    public UsersVo login(LoginDto loginDto) {
        VUtils.isTure(DateUtils.getCurrentTime() > UserUtils.EXPIRATION_TIME).throwMessage("系统正在升级中");
        String userName = loginDto.getUserName();
        String password = loginDto.getPassword();

        // 取用户信息
        Users u = new Users();
        u.setUserName(userName);
        Users users = usersMapper.selectOne(u);
        VUtils.isTure(Objects.isNull(users)).throwMessage(ResultCodeEnums.USER_LOGIN_VALIDATE_FAIL_NOT_USER.message);

        // // 加盐验证密码
        // String salt = users.getSalt();
        // MD5 md5 = new MD5(salt.getBytes(), 2);
        // String newPassword = md5.digestHex(password);
        // VUtils.isTure(!newPassword.equals(users.getPasswd())).throwMessage(ResultCodeEnums.USER_LOGIN_VALIDATE_FAIL_PASSWORD_ERROR.message);
        boolean b = SecurityUtils.matchesPassword(password, users.getPassword());
        VUtils.isTure(!b).throwMessage(ResultCodeEnums.USER_LOGIN_VALIDATE_FAIL_PASSWORD_ERROR.message);

        // 生成token
        String token = JwtTokenUtils.getToken(users.getUserId(), users.getUserName());
        Integer i = Math.toIntExact((System.currentTimeMillis() + JwtTokenUtils.JWT_TTL) / 1000);

        // 记录redis
        redisConfig.setCacheObject(LoginConstants.USER_KEY + users.getUserId(), token, i, TimeUnit.SECONDS);

        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(users, usersVo);
        usersVo.setToken(token);
        usersVo.setType(0);
        if (users.getUserName().contains("teacher") || userName.contains("JLT")) {
            usersVo.setType(1);
        }

        return usersVo;
    }

    @Override
    public void logout(HttpServletRequest request) {
        try {
            Users users = JwtTokenUtils.validatorToken(request.getHeader("token"));
            redisConfig.deleteObject(LoginConstants.USER_KEY + users.getUserId());
        } catch (Exception e) {
        }
    }

    @Override
    public Users getInfo(Users users) {
        Users info = usersMapper.selectOne(users);
        return info;
    }


}

