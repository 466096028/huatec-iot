package com.huatec.iot.iotservice.pojo.dto;

import com.huatec.iot.common.valid.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel("设备DTO模型")
public class DevicesDto {

    @NotNull(message = "ID不能为空", groups = {ValidationGroups.Update.class})
    @Min(value = 1, message = "ID不能为空", groups = {ValidationGroups.Update.class})
    @Max(value = 1111, message = "ID超过最大值", groups = {ValidationGroups.Update.class})
    @ApiModelProperty(value = "ID", position = 1)
    private Integer id;

    @NotNull(message = "产品Id不能为空", groups = {ValidationGroups.Insert.class})
    @Min(value = 1, message = "产品Id不能为空", groups = {ValidationGroups.Insert.class})
    @Max(value = Integer.MAX_VALUE, message = "产品ID超过最大值", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "产品Id", position = 2, required = true)
    private Integer productId;

    @NotBlank(message = "产品key不能为空", groups = {ValidationGroups.Insert.class})
    @Size(min = 0, max = 50, message = "产品key最多为50个字符串", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "产品key", position = 2, required = true)
    private String productKey;

    @NotBlank(message = "设备名称不能为空", groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    @Size(min = 1, max = 50, message = "设备名称最多为50个字符", groups = {ValidationGroups.Insert.class,ValidationGroups.Update.class})
    @ApiModelProperty(value = "设备名称", position = 3, required = true)
    private String deviceName;

    @NotBlank(message = "设备Key不能为空", groups = {ValidationGroups.Insert.class})
    @Size(min = 1, max = 50, message = "设备Key长度最多为50个字符", groups = {ValidationGroups.Insert.class})
    @Pattern(regexp = "([a-zA-Z0-9_-]+)", message = "设备Key支持英文字母、数字、下划线（_）、中划线（-）", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "设备key", position = 4)
    private String deviceKey;

    @ApiModelProperty(value = "是否系统：0不是，1是", required = true)
    private Byte isSystem;

    @ApiModelProperty(value = "创建用户ID", required = true)
    private Integer createUserId;

}