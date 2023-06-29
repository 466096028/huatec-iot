package com.huatec.iot.iotservice.pojo.vo;

import com.huatec.iot.common.enums.DataTypeEnums;
import com.huatec.iot.common.enums.NetTypeEnums;
import com.huatec.iot.common.enums.NodeTypeEnums;
import com.huatec.iot.common.enums.UnitTypeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: huatec-iot-api
 * @description: 产品相关枚举
 * @author: jiangtaohou
 * @create: 2023-04-14 09:33
 **/
@Data
@ApiModel("产品枚举VO模型")
public class ProductEnumsVo {

    @ApiModelProperty(value = "节点类型列表", position = 1)
    private List<NodeTypeEnums.NodeType> NodeTypeList;

    @ApiModelProperty(value = "连网方式列表", position = 2)
    private List<NetTypeEnums.NetType> NetTypeList;

    @ApiModelProperty(value = "数据类型", position = 3)
    private List<DataTypeEnums.DataType> DataTypeList;

    @ApiModelProperty(value = "单位类型", position = 4)
    private List<UnitTypeEnums.UnitType> UnitTypeList;
}
