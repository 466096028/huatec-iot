package com.huatec.iot.iotservice.pojo.qry;

import com.huatec.iot.common.annotation.EnumAnnotation;
import com.huatec.iot.common.enums.DataTypeEnums;
import com.huatec.iot.common.enums.TimeTypeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel("产品模型功能数据Qry模型")
public class ProductModuleAbilityDataQry {

    @ApiModelProperty(value = "设备ID", required = true)
    @Min(value = 0, message = "设备ID不能为空")
    private Integer deviceId;

    @ApiModelProperty(value = "功能标识", required = true)
    @NotBlank(message = "功能标识不能为空")
    private String abilityIdentifier;

    @ApiModelProperty(value = "时间类型：0为自定义，3600为1小时，86400为24小时，604800为7天", required = true)
    @EnumAnnotation(target = TimeTypeEnums.class, message = "时间类型格式不正确")
    private Integer timeType;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date startDateTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date endDateTime;

    @ApiModelProperty(value = "索引", hidden = true)
    private String indexName;
}