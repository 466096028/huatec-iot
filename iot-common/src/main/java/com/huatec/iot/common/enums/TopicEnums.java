package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 通信
 * @author: jiangtaohou
 * @create: 2023-04-14 19:37
 **/
public enum TopicEnums {
    // MQTT系统主题
    CLIENT_ONLINE_EVENT("$SYS/brokers/${node}/clients/${clientid}/connected", "客户端上线事件"),
    CLIENT_OFFLINE_EVENT("$SYS/brokers/${node}/clients/${clientid}/disconnected", "客户端下线事件"),

    // 系统主题
    CLIENT_PROPERTY_POST_EVENT("/sys/${productKey}/${deviceKey}/thing/event/property/post", "设备属性上报"),
    CLIENT_PROPERTY_SET_SERVICE("/sys/${productKey}/${deviceKey}/thing/service/property/set", "属性设置");

    public String code;
    public String desc;

    TopicEnums(String code, String desc) {
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
        for (TopicEnums enums : TopicEnums.values()) {
            if (enums.getCode().equals(code)) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
