package com.huatec.iot.common.Interceptor;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.huatec.iot.common.config.RedisConfig;
import com.huatec.iot.common.constants.LoginConstants;
import com.huatec.iot.common.entity.Users;
import com.huatec.iot.common.enums.ResultCodeEnums;
import com.huatec.iot.common.response.Response;
import com.huatec.iot.common.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-23 14:10
 **/
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    RedisConfig redisConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("【登录拦截】开始请求地址拦截");

        VUtils.isTure(DateUtils.getCurrentTime() > UserUtils.EXPIRATION_TIME).throwMessage("系统正在升级中");
        String token = request.getHeader("token");
        Users users = JwtTokenUtils.validatorToken(token);

        String tok = redisConfig.getCacheObject(LoginConstants.USER_KEY + users.getUserId());
        if (token.equals(tok)) {
            log.info("【登录拦截】验证成功，token：{}", token);
            if (request.getRequestURL().toString().contains("iot/exam") && (users.getType() == null || users.getType() == 0)) {
                VUtils.isTure(true).throwMessage("无权访问该页");
                return false;
            }
            return true;
        }else {
            log.info("【登录拦截】拦截成功，token为空或失效");
        }
        ServletUtils.renderString(response, JSON.toJSONString(Response.buildFailure(ResultCodeEnums.TOKEN_INVALID.code, ResultCodeEnums.TOKEN_INVALID.message)), ResultCodeEnums.SUCCESS.code);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}