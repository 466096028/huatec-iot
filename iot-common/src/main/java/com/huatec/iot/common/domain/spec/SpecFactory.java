package com.huatec.iot.common.domain.spec;

import com.huatec.iot.common.domain.spec.entity.BaseSpec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-27 14:45
 **/
@Component
public class SpecFactory {

    @Autowired
    private Map<String, BaseSpec> specMap;

    /**
     * 规格转换
     *
     * @param type
     * @param json
     * @return
     */
    public Object specTransition(String type, String json) {
        BaseSpec baseSpec = null;
        for (BaseSpec source : specMap.values()) {
            Boolean match = source.match(type);
            if (match) {
                baseSpec = source;
            }
        }
        Object o = null;
        if (!Objects.isNull(baseSpec)) {
            o = baseSpec.analysisSpec(json);
        }
        return o;
    }

    /**
     * 规格值转换
     *
     * @param type
     * @param json
     * @return
     */
    public Object specValueTransition(String type, String json) {
        BaseSpec baseSpec = null;
        for (BaseSpec source : specMap.values()) {
            Boolean match = source.match(type);
            if (match) {
                baseSpec = source;
            }
        }
        Object o = null;
        if (!Objects.isNull(baseSpec)) {
            o = baseSpec.analysisSpecValue(json);
        }
        return o;
    }


    /**
     * 规格值展示
     *
     * @param type
     * @param json
     * @return
     */
    public String specValueShow(String type, String json, String value) {
        BaseSpec baseSpec = null;
        for (BaseSpec source : specMap.values()) {
            Boolean match = source.match(type);
            if (match) {
                baseSpec = source;
            }
        }
        String s = baseSpec.specValueShow(json, value);
        return s;
    }

    /**
     * 规格值
     *
     * @param type
     * @param json
     * @return
     */
    public String specValue(String type, String json, String value) {
        BaseSpec baseSpec = null;
        for (BaseSpec source : specMap.values()) {
            Boolean match = source.match(type);
            if (match) {
                baseSpec = source;
            }
        }
        String s = baseSpec.specValue(json, value);
        return s;
    }

    /**
     * 检查规格输入是否是否正确
     *
     * @param type
     * @param json
     */
    public void checkSpec(String type, String json) {
        for (BaseSpec source : specMap.values()) {
            Boolean match = source.match(type);
            if (match) {
                source.checkSpec(json);
            }
        }
    }
}
