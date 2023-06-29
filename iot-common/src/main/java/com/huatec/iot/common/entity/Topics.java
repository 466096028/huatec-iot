package com.huatec.iot.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "iot_topics")
public class Topics {
    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * 类型：1为基础，2为物模型，3自定义
     */
    @Column(name = "type")
    private Byte type;

    /**
     * 通信地址
     */
    @Column(name = "topic_url")
    private String topicUrl;

    /**
     * 功能类型名称
     */
    @Column(name = "ability_type")
    private String abilityType;

    /**
     * 功能类型名称
     */
    @Column(name = "ability_type_name")
    private String abilityTypeName;

    /**
     * 权限类型：1为发布，2为订阅
     */
    @Column(name = "authorization_type")
    private Byte authorizationType;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 状态:0为启用，1为禁用
     */
    @Column(name = "status")
    private Byte status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}