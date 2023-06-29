package com.huatec.iot.iotservice.pojo.vo;

import com.huatec.iot.common.pojo.BaseVo;
import com.huatec.iot.common.valid.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@ApiModel("设备VO模型")
public class DevicesVo extends BaseVo {
    @ApiModelProperty(value = "ID", position = 1)
    private Integer id;

    @ApiModelProperty(value = "产品ID", position = 2)
    private Integer productId;

    @ApiModelProperty(value = "产品key", position = 2)
    private String productKey;

    @ApiModelProperty(value = "产品名称", position = 3)
    private String productName;

    @ApiModelProperty(value = "节点类型", position = 4)
    private Byte nodeType;

    @ApiModelProperty(value = "节点类型名称", position = 5)
    private String nodeTypeName;

    @ApiModelProperty(value = "设备名称", position = 6)
    private String deviceName;

    @ApiModelProperty(value = "设备key", position = 7)
    private String deviceKey;

    @ApiModelProperty(value = "创建用户ID", position = 8)
    private Integer createUserId;

    @ApiModelProperty(value = "激活时间", position = 9)
    private Date activationTime;

    @ApiModelProperty(value = "最后上线时间", position = 10)
    private Date lastActiveTime;

    @ApiModelProperty(value = "设备状态：0为未激活，1为已激活在线，2为已激活离线", position = 11)
    private Byte deviceStatus;

    @ApiModelProperty(value = "设备状态名称", position = 11)
    private String deviceStatusName;

    @ApiModelProperty(value = "状态:0为启用，1为禁用", position = 12)
    private Byte status;

}