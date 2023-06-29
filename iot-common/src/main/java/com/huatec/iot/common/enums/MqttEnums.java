package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: mqtt枚举
 * @author: jiangtaohou
 * @create: 2023-04-14 19:37
 **/
public enum MqttEnums {
    CONNECT_CLIENT_ID("${productKey}.${deviceKey}", "客户端ID"),
    CONNECT_USERNAME("${deviceKey}&${productKey}", "客户端用户名"),
    CONNECT_PASSWORD("${clientId}${userName}", "客户端密码");

    public String code;
    public String desc;

    MqttEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(String code) {
        String desc = "";
        if (code == null) {
            return desc;
        }
        for (MqttEnums enums : MqttEnums.values()) {
            if (enums.getCode().equals(code)) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
