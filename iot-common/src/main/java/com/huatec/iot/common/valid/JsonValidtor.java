package com.huatec.iot.common.valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huatec.iot.common.annotation.JsonAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: huatec-iot-api
 * @description: 校验json
 * @author: jiangtaohou
 * @create: 2023-04-14 16:50
 **/
public class JsonValidtor implements ConstraintValidator<JsonAnnotation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Object object = JSON.parse(value);
            if (object instanceof JSONObject) {
                return true;
            } else if (object instanceof JSONArray) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
