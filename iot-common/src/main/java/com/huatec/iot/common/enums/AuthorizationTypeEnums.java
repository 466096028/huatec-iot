package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 权限类型
 * @author: jiangtaohou
 * @create: 2023-04-14 19:37
 **/
public enum AuthorizationTypeEnums {

    RELEASE_TYPE(1,"发布"),
    SUBSCRIBE_TYPE(2,"订阅");

    public int code;
    public String desc;

    AuthorizationTypeEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(Byte code) {
        String desc = "";
        if (code == null) {
            return desc;
        }
        for (AuthorizationTypeEnums enums : AuthorizationTypeEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
