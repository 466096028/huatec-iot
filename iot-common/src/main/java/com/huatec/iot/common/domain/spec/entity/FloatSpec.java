package com.huatec.iot.common.domain.spec.entity;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.huatec.iot.common.enums.DataTypeEnums;
import com.huatec.iot.common.enums.RegexpEnums;
import com.huatec.iot.common.utils.VUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @program: huatec-iot-api
 * @description: float规格
 * @author: jiangtaohou
 * @create: 2023-04-27 09:45
 **/
@Data
@Component
@Slf4j
public class FloatSpec implements BaseSpec {
    private float min;
    private float max;
    private String unit;
    private String unitName;
    private double step;

    @Override
    public boolean match(String type) {
        return DataTypeEnums.FLOAT_TYPE.code.equals(type);
    }

    @Override
    public Object analysisSpec(String json) {
        FloatSpec floatSpec = null;
        try {
            floatSpec = JSONUtil.toBean(json, FloatSpec.class);
        } catch (Exception e) {
            log.error("【Float规格】解析异常，exception：{}", e.getMessage());
        }

        return floatSpec;
    }

    @Override
    public Object analysisSpecValue(String json) {
        FloatSpec floatSpec = null;
        try {
            floatSpec = JSONUtil.toBean(json, FloatSpec.class);
        } catch (Exception e) {
            log.error("【Float规格】值解析异常，exception：{}", e.getMessage());
        }

        return floatSpec;
    }

    @Override
    public void checkSpec(String json) {
        log.info("【Float规格】正则检查规格，params：{}", json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        VUtils.isTure(StringUtils.isBlank(jsonObject.getString("min"))).throwMessage("最小值不能为空");
        VUtils.isTure(!RegexpEnums.validateInfo(String.valueOf(jsonObject.getString("min")), RegexpEnums.FLOAT_NUMBER.getRegexp())).throwMessage("最小值必须为浮点类型");

        VUtils.isTure(StringUtils.isBlank(jsonObject.getString("max"))).throwMessage("最大值不能为空");
        VUtils.isTure(!RegexpEnums.validateInfo(String.valueOf(jsonObject.getString("max")), RegexpEnums.FLOAT_NUMBER.getRegexp())).throwMessage("最大值必须为浮点类型");

        VUtils.isTure(StringUtils.isBlank(jsonObject.getString("step"))).throwMessage("步长不能为空");
        VUtils.isTure(!RegexpEnums.validateInfo(String.valueOf(jsonObject.getString("step")), RegexpEnums.POSITIVE_FLOAT_NUMBER.getRegexp())).throwMessage("步长必须为正浮点类型");
    }

    @Override
    public String specValueShow(String json, String value) {

        FloatSpec spec = (FloatSpec) analysisSpecValue(json);

        return (StringUtils.isNotBlank(value) ? value : "") + spec.getUnitName();
    }

    @Override
    public String specValue(String json, String value) {
        return StringUtils.isNotBlank(value) ? value : "";
    }
}
