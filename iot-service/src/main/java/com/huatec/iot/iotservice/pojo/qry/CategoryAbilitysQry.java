package com.huatec.iot.iotservice.pojo.qry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ApiModel("分类功能Qry模型")
public class CategoryAbilitysQry {

    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID")
    private Integer categoryId;

}