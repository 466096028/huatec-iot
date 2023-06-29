package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 删除状态枚举
 * @author: jiangtaohou
 * @create: 2023-04-13 21:38
 **/
public enum DeleteEnums {

    UNDELETED(0, "未删除"),
    DELETED(1, "已删除");

    public int code;
    public String desc;

    DeleteEnums(int code, String desc) {
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
        for (DeleteEnums enums : DeleteEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
