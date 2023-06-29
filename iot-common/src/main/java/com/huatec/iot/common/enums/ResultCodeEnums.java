package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 错误枚举
 * @author: jiangtaohou
 * @create: 2023-04-13 21:38
 **/
public enum ResultCodeEnums {
    // 成功
    SUCCESS(200, "成功"),
    CREATED(201, "对象创建成功"),
    ACCEPTED(202, "请求已经被接受"),
    NO_CONTENT(204, "操作已经执行成功，但是没有返回数据"),

    MOVED_PERM(301, "资源已被移除"),
    SEE_OTHER(303, "重定向"),
    NOT_MODIFIED(304, "资源没有被修改"),

    // 失败
    FAIL(400, "失败"),
    // 未认证（签名错误）
    UNAUTHORIZED(401, "未认证（签名错误）"),
    FORBIDDEN(403, "访问受限，授权过期"),
    INVALID_SIGNATURE(404, "无效签名"),
    TOKEN_EXPIRATION(405, "token过期"),
    TOKEN_ALGORITHM_INCONSISTENCY(406, "token算法不一致"),
    TOKEN_INVALID(407, "token无效"),


    // 接口不存在
    NOT_FOUND(414, "资源，服务未找到"),
    BAD_METHOD(415, "不允许的http方法"),
    CONFLICT(416, "资源冲突，或者资源被锁"),
    UNSUPPORTED_TYPE(417, "不支持的数据，媒体类型"),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    // EXCEL 导入失败问题
    IMPORT_ERROR(501, "导入的数据存在格式问题,请检查并修改后重试!"),

    // 参数错误
    PARAMS_VALID_FAIL(601, "参数验证失败"),

    // 登录错误
    USER_LOGIN_VALIDATE_FAIL_NOT_USER(701, "用户不存在,登录失败！"),
    USER_LOGIN_VALIDATE_FAIL_PASSWORD_ERROR(702, "密码错误,登录失败！"),
    USER_LOGIN_VALIDATE_SUCCESS(703, "用户登录验证成功！"),
    USER_LOGIN_AUTHENTICATION_FAILED(704, "用户验证失败！");


    public int code;
    public String message;

    ResultCodeEnums(int code, String message) {
        this.code = code;
        this.message = message;

    }
}
