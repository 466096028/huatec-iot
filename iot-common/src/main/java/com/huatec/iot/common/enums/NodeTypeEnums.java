package com.huatec.iot.common.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 节点类型枚举
 * @author: jiangtaohou
 * @create: 2023-04-13 21:38
 **/

public enum NodeTypeEnums {

    DEVICE_DIRECT_CONNECTION(1, "直连设备");
    // DEVICE_GATEWAY_SUBDEVICE(2, "网关子设备"),
    // DEVICE_GATEWAY_DEVICE(3, "网关设备");

    public int code;
    public String desc;

    NodeTypeEnums(int code, String desc) {
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
        for (NodeTypeEnums enums : NodeTypeEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }

    @Data
    @ApiModel("节点类型")
    public static class NodeType {
        @ApiModelProperty(value = "节点类型编码", position = 1)
        public int code;
        @ApiModelProperty(value = "描述", position = 2)
        public String desc;

        public static List<NodeType> getList() {
            List<NodeType> list = new ArrayList<>();
            for (NodeTypeEnums enums : NodeTypeEnums.values()) {
                NodeType nodeType = new NodeType();
                nodeType.setCode(enums.getCode());
                nodeType.setDesc(enums.getDesc());
                list.add(nodeType);
            }
            return list;
        }
    }
}
