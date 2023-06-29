package com.huatec.iot.iotservice.pojo.dto;

import com.huatec.iot.common.annotation.EnumAnnotation;
import com.huatec.iot.common.enums.NetTypeEnums;
import com.huatec.iot.common.enums.NodeTypeEnums;
import com.huatec.iot.common.valid.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel("产品DTO模型")
public class ProductsDto {

    @NotNull(message = "ID不能为空", groups = {ValidationGroups.Update.class})
    @Min(value = 1, message = "ID不能为空", groups = {ValidationGroups.Update.class})
    @Max(value = Integer.MAX_VALUE, message = "ID超过最大值", groups = {ValidationGroups.Update.class})
    @ApiModelProperty(value = "ID", position = 1)
    private Integer id;

    // @ApiModelProperty(value = "产品key", position = 2, hidden = true)
    // private String productKey;

    @NotBlank(message = "产品名称不能为空", groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    @Size(max = 50, message = "产品名称长度最长为50个字符", groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    @ApiModelProperty(value = "产品名称", position = 3, required = true)
    private String productName;

    @NotNull(message = "分类Id不能为空", groups = {ValidationGroups.Insert.class})
    @Min(value = 1, message = "分类Id不能为空", groups = {ValidationGroups.Insert.class})
    @Max(value = Integer.MAX_VALUE, message = "分类ID超过最大值", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "分类Id", position = 4, required = true)
    private Integer categoryId;

    @NotNull(message = "节点类型不能为空", groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    @EnumAnnotation(message = "节点类型必须为指定值", target = NodeTypeEnums.class, groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    @ApiModelProperty(value = "节点类型", position = 7, required = true)
    private Byte nodeType;

    @NotNull(message = "连网方式不能为空", groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    @EnumAnnotation(message = "连网方式必须为指定值", target = NetTypeEnums.class, groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    @ApiModelProperty(value = "连网方式", position = 8, required = true)
    private Byte netType;

    @ApiModelProperty(value = "数据格式", position = 9, hidden = true)
    private Byte dataFormat = 1;

    @ApiModelProperty(value = "描述", position = 10)
    @Size(min = 0, max = 100, message = "描述内容长度最多为100个字符串", groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    private String description;

    @ApiModelProperty(value = "产品状态", position = 11, hidden = true)
    private Byte status;

    @ApiModelProperty(value = "是否系统：0不是，1是", required = true)
    private Byte isSystem;

    @ApiModelProperty(value = "创建用户ID", required = true)
    private Integer createUserId;
}