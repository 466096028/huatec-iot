package com.huatec.iot.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.huatec.iot.common.constants.LoginConstants;
import com.huatec.iot.common.entity.Users;
import com.huatec.iot.common.enums.ResultCodeEnums;
import com.huatec.iot.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-23 15:40
 **/
@Slf4j
public class JwtTokenUtils {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 * 24 * 1000L;// 一个小时
    //设置秘钥明文
    private static final String SIGN = "282CCACCC3EA34A09444F56508B8EF87";

    /**
     * 生成token
     *
     * @param userId
     * @return
     */
    public static String getToken(Integer userId, String userName) {
        Integer type = 0;
        if (userName.contains("teacher") || userName.contains("JLT")) {
            type = 1;
        }
        String token = JWT.create()
                //主题
                // .withSubject("token")
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withClaim("type", type)

                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TTL))
                .sign(Algorithm.HMAC256(SIGN));

        return token;
    }

    public static Users validatorToken(String token) {
        Users users = new Users();
        String error = "";
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGN)).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            Integer userId = verify.getClaim("userId").asInt();
            String userName = verify.getClaim("userName").asString();
            Integer type = verify.getClaim("type").asInt();

            users.setUserId(userId);
            users.setUserName(userName);
            users.setType(type);

        } catch (SignatureVerificationException e) {
            log.error("【登录拦截】无效签名，token：{}", token);
            error = ResultCodeEnums.INVALID_SIGNATURE.message;
        } catch (TokenExpiredException e) {
            log.error("【登录拦截】token过期，token：{}", token);
            error = ResultCodeEnums.TOKEN_EXPIRATION.message;
        } catch (AlgorithmMismatchException e) {
            log.error("【登录拦截】token算法不一致，token：{}", token);
            error = ResultCodeEnums.TOKEN_ALGORITHM_INCONSISTENCY.message;
        } catch (Exception e) {
            log.error("【登录拦截】token无效，token：{}", token);
            error = ResultCodeEnums.TOKEN_INVALID.message;
        }
        if (StringUtils.isNotBlank(error)) {
            throw new ServiceException(error, ResultCodeEnums.TOKEN_INVALID.code);
        }

        return users;
    }
}
