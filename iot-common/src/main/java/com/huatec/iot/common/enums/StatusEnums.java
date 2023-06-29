package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 状态类型枚举
 * @author: jiangtaohou
 * @create: 2023-04-13 21:38
 **/
public enum StatusEnums {

    ENABLE_STATUS(0, "启用"),
    FORBIDDEN_STATUS(1, "禁用");

    public int code;
    public String desc;

    StatusEnums(int code, String desc) {
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
        for (StatusEnums enums : StatusEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
