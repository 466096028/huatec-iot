package com.huatec.iot.common.enums;

/**
 * @program: huatec-iot-api
 * @description: 数据格式枚举
 * @author: jiangtaohou
 * @create: 2023-04-13 21:38
 **/
public enum DataFormatEnums {

    FORMAT_JSON(1, "ICA标准数据格式（Alink JSON）");

    public int code;
    public String desc;

    DataFormatEnums(int code, String desc) {
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
        for (DataFormatEnums enums : DataFormatEnums.values()) {
            if (code == enums.getCode()) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }
}
