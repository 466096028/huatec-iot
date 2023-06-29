package com.huatec.iot.common.domain.spec.entity;


/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-27 11:36
 **/
public interface BaseSpec{

    boolean match(String type);

    Object analysisSpec(String json);

    Object analysisSpecValue(String json);

    void checkSpec(String json);

    String specValueShow(String json, String value);

    String specValue(String json, String value);
}
