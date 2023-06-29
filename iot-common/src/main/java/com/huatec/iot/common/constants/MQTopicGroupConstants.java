package com.huatec.iot.common.constants;

/**
 * @program: ums-app-sms-api
 * @description:
 * @author: jiangtaohou
 * @create: 2022-03-21 18:26
 **/
public interface MQTopicGroupConstants {
    // 上线主题和组
    String DEVICE_ONLINE_CONSUMER_GROUP = "Device_Online_Consumer_Group";
    String DEVICE_ONLINE_TOPIC = "Device_Online_Topic";

    // 下线主题和组
    String DEVICE_OFFLINE_CONSUMER_GROUP = "Device_Offline_Consumer_Group";
    String DEVICE_OFFLINE_TOPIC = "Device_Offline_Topic";

    // 属性上报主题和组
    String DEVICE_PROPERTY_POST_COMSUMER_GROUP = "Device_Property_Post_Consumer_Group";
    String DEVICE_PROPERTY_POST_TOPIC = "Device_Property_Post_Topic";

    // 属性设置主题和组
    String PROPERTY_SET_COMSUMER_GROUP = "Property_Set_Consumer_Group";
    String PROPERTY_SET_COMSUMER_TOPIC = "Property_Set_Consumer_Topic";
}
