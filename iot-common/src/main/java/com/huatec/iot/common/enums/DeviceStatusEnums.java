package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 连网类型枚举
 * @author: jiangtaohou
 * @create: 2023-04-13 21:38
 **/
public enum DeviceStatusEnums {
    UNACTIVATED_STATUS(0, "未激活"),
    ACTIVATED_ONLINE_STATUS(1, "已激活,在线"),
    ACTIVATED_OFFLINE_STATUS(2, "已激活,离线");

    public int code;
    public String desc;

    DeviceStatusEnums(int code, String desc) {
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
        for (DeviceStatusEnums enums : DeviceStatusEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
