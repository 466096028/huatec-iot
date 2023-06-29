package com.huatec.iot.iotservice.pojo.vo;

import com.huatec.iot.common.domain.spec.entity.BaseSpec;
import lombok.Data;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 物模型DSL
 * @author: jiangtaohou
 * @create: 2023-04-27 11:30
 **/
@Data
public class ThingModelVo {
    // private String schema;
    private Profile profile;
    private List<Properties> properties;
    // private List<Events> events;
    // private List<Services> services;
    // private List<FunctionBlocks> functionBlocks;

    @Data
    public static class Profile {
        private String version;
        private String productKey;
    }

    @Data
    public static class Properties {
        private String identifier;
        private String name;
        private boolean required;
        private DataType dataType;
    }

    @Data
    public static class DataType {
        private String type;
        private Object specs;
    }
}
