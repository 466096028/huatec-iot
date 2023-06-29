package com.huatec.iot.iotservice.pojo.vo;

import com.huatec.iot.common.pojo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分类功能VO模型")
public class CategoryAbilitysVo extends BaseVo {
    @ApiModelProperty(value = "ID", position = 1)
    private Integer id;

    /**
     * 功能名称
     */
    @ApiModelProperty(value = "功能名称", position = 2)
    private String abilityName;

    /**
     * 功能标识
     */
    @ApiModelProperty(value = "功能标识", position = 3)
    private String abilityIdentifier;

    /**
     * 功能类型
     */
    @ApiModelProperty(value = "功能类型", position = 4)
    private Byte abilityType;

    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID", position = 5)
    private Integer categoryId;

    /**
     * 分类key
     */
    @ApiModelProperty(value = "分类key", position = 6)
    private String categoryKey;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称", position = 7)
    private String categoryName;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "数据类型", position = 8)
    private String dataType;

    @ApiModelProperty(value = "数据类型名称", position = 8)
    private String dataTypeName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", position = 9)
    private String description;

    /**
     * 数据规格
     */
    @ApiModelProperty(value = "数据规格", position = 10)
    private Object dataSpecs;

    /**
     * 是否必选 0:非必选;1:必选
     */
    @ApiModelProperty(value = "是否必选 0:非必选;1:必选", position = 11)
    private Byte isRequired;

    /**
     * 是否标准 0:非标准;1:标准
     */
    @ApiModelProperty(value = "是否标准 0:非标准;1:标准", position = 12)
    private Byte isStandard;

    /**
     * 是否删除 0:未删除;1:已删除
     */
    @ApiModelProperty(value = "是否删除 0:未删除;1:已删除", position = 13)
    private Integer isDel;


}