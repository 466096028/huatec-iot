package com.huatec.iot.iotservice.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @program: huatec-iot-api
 * @description:
 * @author: jiangtaohou
 * @create: 2023-04-23 14:30
 **/
@Data
@ApiModel("登录Dto模型")
public class LoginDto {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", position = 1, required = true)
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 1, message = "密码不能为空")
    @ApiModelProperty(value = "密码", position = 2, required = true)
    private String password;
}
