package com.huatec.iot.common.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 数据类型
 * @author: jiangtaohou
 * @create: 2023-04-14 19:37
 **/
public enum DataTypeEnums {

    INT_TYPE("int", "整数型"),
    FLOAT_TYPE("float", "单精度浮点型"),
    DOUBLE_TYPE("double", "双精度浮点型"),
    ENUM_TYPE("enum", "枚举型"),
    BOOL_TYPE("bool", "布尔型");
    // TEXT_TYPE("text", "字符串");
    // DATE_TYPE("date", "时间型"),
    // STRUCT_TYPE("struct", "结构体"),
    // ARRAY_TYPE("array", "数组");

    public String code;
    public String desc;

    DataTypeEnums(String code, String desc) {
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
        for (DataTypeEnums enums : DataTypeEnums.values()) {
            if (code.equals(enums.getCode())) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }

    @Data
    @ApiModel("数据类型")
    public static class DataType {
        @ApiModelProperty(value = "数据类型编码", position = 1)
        public String code;
        @ApiModelProperty(value = "描述", position = 2)
        public String desc;

        public static List<DataTypeEnums.DataType> getList() {
            List<DataTypeEnums.DataType> list = new ArrayList<>();
            for (DataTypeEnums enums : DataTypeEnums.values()) {
                DataTypeEnums.DataType netType = new DataTypeEnums.DataType();
                netType.setCode(enums.getCode());
                netType.setDesc(enums.getDesc());
                list.add(netType);
            }
            return list;
        }
    }
}
