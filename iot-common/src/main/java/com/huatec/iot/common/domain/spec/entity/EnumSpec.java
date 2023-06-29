package com.huatec.iot.common.domain.spec.entity;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huatec.iot.common.enums.DataTypeEnums;
import com.huatec.iot.common.utils.VUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: huatec-iot-api
 * @description: 枚举型规格
 * @author: jiangtaohou
 * @create: 2023-04-27 09:45
 **/
@Data
@Component
@Slf4j
public class EnumSpec implements BaseSpec {
    // 名称
    private String name;
    // 值
    private String value;

    @Override
    public boolean match(String type) {
        return DataTypeEnums.ENUM_TYPE.code.equals(type);
    }


    @Override
    public Object analysisSpec(String json) {
        ArrayList<EnumSpec> list = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(json)) {
                JSONArray objects = JSONUtil.parseArray(json);
                objects.forEach(item -> {
                    EnumSpec booleanSpec = JSONUtil.toBean((JSONObject) item, EnumSpec.class);
                    list.add(booleanSpec);
                });
            }
        } catch (Exception e) {
            log.error("【Enum规格】解析异常，exception：{}", e.getMessage());
        }

        return list;
    }

    @Override
    public Object analysisSpecValue(String json) {
        HashMap<String, String> map = new HashMap<>();
        try {
            if (StringUtils.isNotBlank(json)) {
                JSONArray objects = JSONUtil.parseArray(json);
                objects.forEach(item -> {
                    EnumSpec enumSpec = JSONUtil.toBean((JSONObject) item, EnumSpec.class);
                    map.put(enumSpec.name, enumSpec.value);
                });
            }
        } catch (Exception e) {
            log.error("【Enum规格】值解析异常，exception：{}", e.getMessage());
        }

        return map;
    }

    @Override
    public void checkSpec(String json) {
        log.info("【Enum规格】正则检查规格，params：{}", json);
        if (StringUtils.isNotBlank(json)) {
            JSONArray objects = JSONUtil.parseArray(json);
            objects.forEach(item -> {
                EnumSpec enumSpec = JSONUtil.toBean((JSONObject) item, EnumSpec.class);
                VUtils.isTure(StringUtils.isBlank(enumSpec.getName())).throwMessage("键不能为空");
                VUtils.isTure(StringUtils.isBlank(enumSpec.getValue())).throwMessage("值不能为空");
            });
        }
    }

    @Override
    public String specValueShow(String json, String value) {
        HashMap<String, String> hashMap = (HashMap) analysisSpecValue(json);
        return StringUtils.isNotBlank(hashMap.get(value)) ? hashMap.get(value) : "";
    }


    @Override
    public String specValue(String json, String value) {
        HashMap<String, String> hashMap = (HashMap) analysisSpecValue(json);
        return StringUtils.isNotBlank(hashMap.get(value)) ? hashMap.get(value) : "";
    }
}
