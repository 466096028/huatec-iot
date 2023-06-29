package com.huatec.iot.common.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "iot_devices")
@ToString
@Data
public class Devices {
    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 产品key
     */
    @Column(name = "product_key")
    private String productKey;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 设备key
     */
    @Column(name = "device_key")
    private String deviceKey;

    /**
     * 创建用户ID
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 设备状态：0为未激活，1为已激活在线，2为已激活离线
     */
    @Column(name = "device_status")
    private Byte deviceStatus;

    /**
     * 是否系统：0为否，1为是
     */
    @Column(name = "is_system")
    private Byte isSystem;

    /**
     * 状态:0为启用，1为禁用
     */
    @Column(name = "status")
    private Byte status;

    @Column(name = "activation_time")
    private Date activationTime;

    @Column(name = "last_active_time")
    private Date lastActiveTime;

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

    @Transient
    private Products products;

}