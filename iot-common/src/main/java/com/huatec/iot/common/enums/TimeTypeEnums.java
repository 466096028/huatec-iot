package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 数据时间枚举
 * @author: jiangtaohou
 * @create: 2023-04-13 21:38
 **/
public enum TimeTypeEnums {

    ONE_HOURS(3600, "1小时"),
    TWENTY_FOUR_HOURS(86400, "24小时"),
    SEVEN_DAYS(604800, "7天"),
    CUSTOMIZE(0, "自定义");

    public int code;
    public String desc;

    TimeTypeEnums(int code, String desc) {
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
        for (TimeTypeEnums enums : TimeTypeEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
