package com.huatec.iot.common.utils;

import com.huatec.iot.common.entity.Users;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-24 09:59
 **/
public class UserUtils {
    public static final Long EXPIRATION_TIME = 1693324800000L;
    // public static final Long EXPIRATION_TIME = 1683707358369L;

    public static Users getUserInfo() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        Users users = JwtTokenUtils.validatorToken(token);

        return users;
    }
}
