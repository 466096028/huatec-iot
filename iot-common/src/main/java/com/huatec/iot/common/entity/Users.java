package com.huatec.iot.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "sys_user")
@Data
public class Users {
    @Id
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "avatar")
    private String avatar;
    /**
     * 登录密码  MD5 加密
     */
    @Column(name = "password")
    private String password;

    @Transient
    private Integer type;
}