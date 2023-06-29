package com.huatec.iot.iotservice.pojo.dto;

import com.huatec.iot.common.annotation.EnumAnnotation;
import com.huatec.iot.common.annotation.JsonAnnotation;
import com.huatec.iot.common.enums.DataTypeEnums;
import com.huatec.iot.common.valid.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel("产品模块功能DTO模型")
public class ProductModuleAbilitysDto {

    @NotNull(groups = {ValidationGroups.Update.class})
    @Min(value = 1, groups = {ValidationGroups.Update.class})
    @Max(value = Integer.MAX_VALUE, message = "ID超过最大值", groups = {ValidationGroups.Update.class})
    @ApiModelProperty(value = "ID", position = 1)
    private Integer id;

    @ApiModelProperty(value = "模块ID", position = 2)
    private Integer moduleId = 0;

    @NotNull(message = "产品ID不能为空", groups = {ValidationGroups.Insert.class})
    @Min(value = 1, message = "产品ID不能为空", groups = {ValidationGroups.Insert.class})
    @Max(value = Integer.MAX_VALUE, message = "产品ID超过最大值", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "产品ID", position = 3, required = true)
    private Integer productId;

    @NotBlank(message = "功能名称不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @Size(min = 0, max = 50, message = "功能名称最多为50个字符串", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "功能名称", position = 4, required = true)
    private String abilityName;

    @NotBlank(message = "功能标识不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @Size(min = 0, max = 50, message = "功能标识最多为50个字符串", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "功能标识", position = 5, required = true)
    private String abilityIdentifier;

    @ApiModelProperty(value = "功能类型", position = 6, hidden = true)
    private Byte abilityType = 1;

    @NotBlank(message = "数据类型不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @EnumAnnotation(message = "数据类型必须为指定值", target = DataTypeEnums.class)
    @ApiModelProperty(value = "数据类型", position = 7, required = true)
    private String dataType;

    @Size(min = 0, max = 100, message = "描述最多为100个字符串", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "描述", position = 8)
    private String description;

    @ApiModelProperty(value = "是否必选 0:非必选;1:必选", position = 9)
    private Byte isRequired = 0;

    @ApiModelProperty(value = "是否标准", position = 10, hidden = true)
    private Byte isStandard = 0;

    @ApiModelProperty(value = "创建用户ID", position = 11, hidden = true)
    private Integer createUserId;

    @NotBlank(message = "数据规格不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @JsonAnnotation(message = "数据规格必须为json串", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @ApiModelProperty(value = "数据规格", position = 12, required = true)
    private String dataSpecs;

}