package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 功能类型枚举
 * @author: jiangtaohou
 * @create: 2023-05-05 09:04
 **/
public enum AbilityTypeEnums {
    ATTRIBUTE(1, "属性");

    public int code;
    public String desc;

    AbilityTypeEnums(int code, String desc) {
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
        for (AbilityTypeEnums enums : AbilityTypeEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
