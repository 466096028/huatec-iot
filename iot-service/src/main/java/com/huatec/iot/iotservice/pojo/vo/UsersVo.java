package com.huatec.iot.iotservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsersVo {

    @ApiModelProperty(value = "用户ID", position = 1)
    private Integer userId;

    @ApiModelProperty(value = "用户账号", position = 2)
    private String userName;

    @ApiModelProperty(value = "用户昵称", position = 3)
    private String nickName;

    @ApiModelProperty(value = "用户头像", position = 4)
    private String avatar;

    @ApiModelProperty(value = "用户类型：0为学生，1为老师", position = 5)
    private Integer type;

    @ApiModelProperty(value = "token", position = 10)
    private String token;

}