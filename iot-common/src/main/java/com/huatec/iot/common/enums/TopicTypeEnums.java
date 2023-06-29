package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 通信类型
 * @author: jiangtaohou
 * @create: 2023-04-14 19:37
 **/
public enum TopicTypeEnums {
    BASE_TYPE(1,"基础通信Topic"),
    OBJECT_MODEL_TYPE(2,"物模型通信Topic"),
    CUSTOM_TYPE(3,"自定义Topic");

    public int code;
    public String desc;

    TopicTypeEnums(int code, String desc) {
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
        for (TopicTypeEnums enums : TopicTypeEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
