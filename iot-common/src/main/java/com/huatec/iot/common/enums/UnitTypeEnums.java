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
public enum UnitTypeEnums {
    MG_KG_TYPE("mg/kg", "毫克每千克"),
    NTU_TYPE("NTU", "浊度"),
    PH_TYPE("pH", "PH值"),
    DS_M_TYPE("dS/m", "土壤EC值"),
    WM2_TYPE("W/㎡", "太阳总辐射"),
    MM_HOUR_TYPE("mm/hour", "降雨量"),
    VAR_TYPE("var", "乏"),
    CP_TYPE("cP", "厘泊"),
    AW_TYPE("aw", "饱和度"),
    PCS_TYPE("pcs", "个"),
    CST_TYPE("cst", "厘斯"),
    BAR_TYPE("bar", "巴"),
    PPT_TYPE("ppt", "纳克每升"),
    PPB_TYPE("ppb", "微克每升"),
    US_CM_TYPE("uS/cm", "微西每厘米"),
    N_C_TYPE("N/C", "牛顿每库仑"),
    V_M_TYPE("V/m", "伏特每米"),
    ML_MIN_TYPE("ml/min", "滴速"),
    MMHG_TYPE("mmHg", "血压"),
    MMOL_L_TYPE("mmol/L", "血糖"),
    MM_S_TYPE("mm/s", "毫米每秒"),
    TURN_M_TYPE("turn/m", "转每分钟"),
    COUNT_TYPE("count", "次"),
    GEAR_TYPE("gear", "档"),
    STEPCOUNT_TYPE("stepCount", "步"),
    NM3_H_TYPE("Nm3/h", "标准立方米每小时"),
    KV_TYPE("kV", "千伏"),
    KVA_TYPE("kVA", "千伏安"),
    KVAR_TYPE("kVar", "千乏"),
    UW_CM2_TYPE("uw/cm2", "微瓦每平方厘米"),
    ONLY_TYPE("只", "只"),
    RH_TYPE("%RH", "相对湿度"),
    M3_S_TYPE("m³/s", "立方米每秒"),
    KG_S_TYPE("kg/s", "公斤每秒"),
    R_MIN_TYPE("r/min", "转每分钟"),
    T_H_TYPE("t/h", "吨每小时"),
    KCL_H_TYPE("KCL/h", "千卡每小时"),
    L_S_TYPE("L/s", "升每秒"),
    MPA_TYPE("Mpa", "兆帕"),
    M3_H_TYPE("m³/h", "立方米每小时"),
    KVARH_TYPE("kvarh", "千乏时"),
    ΜG_L_TYPE("μg/L", "微克每升"),
    KCAL_TYPE("kcal", "千卡路里"),
    GB_TYPE("GB", "吉字节"),
    MB_TYPE("MB", "兆字节"),
    KB_TYPE("KB", "千字节"),
    B_TYPE("B", "字节"),
    ΜG_DM2_D_TYPE("μg/(d㎡·d)", "微克每平方分米每天"),
    EMPTY_TYPE("", "无"),
    PPM_TYPE("ppm", "百万分率"),
    PIXEL_TYPE("pixel", "像素"),
    LUX_TYPE("Lux", "照度"),
    GRAV_TYPE("grav", "重力加速度"),
    DB_TYPE("dB", "分贝"),
    PERCENT_TYPE("%", "百分比"),
    LM_TYPE("lm", "流明"),
    BIT_TYPE("bit", "比特"),
    G_ML_TYPE("g/mL", "克每毫升"),
    G_L_TYPE("g/L", "克每升"),
    MG_L_TYPE("mg/L", "毫克每升"),
    ΜG_M3_TYPE("μg/m³", "微克每立方米"),
    MG_M3_TYPE("mg/m³", "毫克每立方米"),
    G_M3_TYPE("g/m³", "克每立方米"),
    KG_M3_TYPE("kg/m³", "千克每立方米"),
    NF_TYPE("nF", "纳法"),
    PF_TYPE("pF", "皮法"),
    ΜF_TYPE("μF", "微法"),
    F_TYPE("F", "法拉"),
    Ω_TYPE("Ω", "欧姆"),
    ΜA_TYPE("μA", "微安"),
    MA_TYPE("mA", "毫安"),
    KA_TYPE("kA", "千安"),
    A_TYPE("A", "安培"),
    MV_TYPE("mV", "毫伏"),
    V_TYPE("V", "伏特"),
    MS_TYPE("ms", "毫秒"),
    S_TYPE("s", "秒"),
    MIN_TYPE("min", "分钟"),
    H_TYPE("h", "小时"),
    DAY_TYPE("day", "日"),
    WEEK_TYPE("week", "周"),
    MONTH_TYPE("month", "月"),
    YEAR_TYPE("year", "年"),
    KN_TYPE("kn", "节"),
    KM_H_TYPE("km/h", "千米每小时"),
    M_S_TYPE("m/s", "米每秒"),
    DOUBLE_QUOTATION_TYPE("″", "秒"),
    SINGLE_QUOTATION_TYPE("′", "分"),
    PERIOD_TYPE("°", "度"),
    RAD_TYPE("rad", "弧度"),
    HZ_TYPE("Hz", "赫兹"),
    ΜW_TYPE("μW", "微瓦"),
    MW_TYPE("mW", "毫瓦"),
    KW_TYPE("kW", "千瓦特"),
    W_TYPE("W", "瓦特"),
    CAL_TYPE("cal", "卡路里"),
    KW_H_TYPE("kW·h", "千瓦时"),
    WH_TYPE("Wh", "瓦时"),
    EV_TYPE("eV", "电子伏"),
    KJ_TYPE("kJ", "千焦"),
    J_TYPE("J", "焦耳"),
    FAHRENHEIT_TYPE("℉", "华氏度"),
    K_TYPE("K", "开尔文"),
    T_TYPE("t", "吨"),
    DEGREE_CENTIGRADE_TYPE("°C", "摄氏度"),
    MPA1_TYPE("mPa", "毫帕"),
    HPA_TYPE("hPa", "百帕"),
    KPA_TYPE("kPa", "千帕"),
    PA_TYPE("Pa", "帕斯卡"),
    MG_TYPE("mg", "毫克"),
    G_TYPE("g", "克"),
    KG_TYPE("kg", "千克"),
    N_TYPE("N", "牛"),
    ML_TYPE("mL", "毫升"),
    L_TYPE("L", "升"),
    MM3_TYPE("mm³", "立方毫米"),
    CM3_TYPE("cm³", "立方厘米"),
    KM3_TYPE("km³", "立方千米"),
    M3_TYPE("m³", "立方米"),
    HM2_TYPE("h㎡", "公顷"),
    CM2_TYPE("c㎡", "平方厘米"),
    MM2_TYPE("m㎡", "平方毫米"),
    KM2_TYPE("k㎡", "平方千米"),
    M2_TYPE("㎡", "平方米"),
    NM_TYPE("nm", "纳米"),
    ΜM_TYPE("μm", "微米"),
    MM_TYPE("mm", "毫米"),
    CM_TYPE("cm", "厘米"),
    DM_TYPE("dm", "分米"),
    KM_TYPE("km", "千米"),
    M_TYPE("m", "米");


    public String code;
    public String desc;

    UnitTypeEnums(String code, String desc) {
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
        for (UnitTypeEnums enums : UnitTypeEnums.values()) {
            if (code.equals(enums.getCode())) {
                desc = enums.getDesc();
                break;
            }
        }
        return desc;
    }

    @Data
    @ApiModel("单位类型")
    public static class UnitType {
        @ApiModelProperty(value = "单位类型编码", position = 1)
        public String code;
        @ApiModelProperty(value = "描述", position = 2)
        public String desc;

        public static List<UnitTypeEnums.UnitType> getList() {
            List<UnitTypeEnums.UnitType> list = new ArrayList<>();
            for (UnitTypeEnums enums : UnitTypeEnums.values()) {
                UnitTypeEnums.UnitType netType = new UnitTypeEnums.UnitType();
                netType.setCode(enums.getCode());
                netType.setDesc(enums.getDesc());
                list.add(netType);
            }
            return list;
        }
    }
}
