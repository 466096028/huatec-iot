package com.huatec.iot.common.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 连网类型枚举
 * @author: jiangtaohou
 * @create: 2023-04-13 21:38
 **/
public enum NetTypeEnums {
    WI_FI(1, Arrays.asList(NodeTypeEnums.DEVICE_DIRECT_CONNECTION.getCode()), "Wi-Fi");
    //
    // WI_FI(1, Arrays.asList(NodeTypeEnums.DEVICE_DIRECT_CONNECTION.getCode(), NodeTypeEnums.DEVICE_GATEWAY_DEVICE.getCode()), "Wi-Fi"),
    // CUSTOMIZE(2, Arrays.asList(NodeTypeEnums.DEVICE_GATEWAY_SUBDEVICE.getCode()), "自定义"),
    // ETHERNET(3, Arrays.asList(NodeTypeEnums.DEVICE_DIRECT_CONNECTION.getCode(), NodeTypeEnums.DEVICE_GATEWAY_DEVICE.getCode()), "以太网");

    public int code;
    public List<Integer> nodeTypeCode;
    public String desc;

    NetTypeEnums(int code, List<Integer> nodeTypeCode, String desc) {
        this.code = code;
        this.nodeTypeCode = nodeTypeCode;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Integer> getNodeTypeCode() {
        return nodeTypeCode;
    }

    public void setNodeTypeCode(List<Integer> nodeTypeCode) {
        this.nodeTypeCode = nodeTypeCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(Byte code){
        String desc = "";
        if (code == null) {
            return desc;
        }
        for(NetTypeEnums enums : NetTypeEnums.values()){
            if(code == enums.getCode()){
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }

    @Data
    @ApiModel("连网方式")
    public static class NetType {
        @ApiModelProperty(value = "连网方式编码", position = 1)
        public int code;
        @ApiModelProperty(value = "节点编码数组", position = 2)
        public List<Integer> nodeTypeCode;
        @ApiModelProperty(value = "描述", position = 3)
        public String desc;

        public static List<NetTypeEnums.NetType> getList() {
            List<NetTypeEnums.NetType> list = new ArrayList<>();
            for (NetTypeEnums enums : NetTypeEnums.values()) {
                NetTypeEnums.NetType netType = new NetTypeEnums.NetType();
                netType.setCode(enums.getCode());
                netType.setDesc(enums.getDesc());
                netType.setNodeTypeCode(enums.getNodeTypeCode());
                list.add(netType);
            }
            return list;
        }
    }
}
