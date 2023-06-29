package com.huatec.iot.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "iot_category_abilitys")
public class CategoryAbilitys {
    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * 功能名称
     */
    @Column(name = "ability_name")
    private String abilityName;

    /**
     * 功能标识
     */
    @Column(name = "ability_identifier")
    private String abilityIdentifier;

    /**
     * 功能类型
     */
    @Column(name = "ability_type")
    private Byte abilityType;

    /**
     * 分类ID
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 分类key
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 分类名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 数据类型
     */
    @Column(name = "data_type")
    private String dataType;

    /**
     * 描述
     */
    private String description;

    /**
     * 数据规格
     */
    @Column(name = "data_specs")
    private String dataSpecs;

    /**
     * 是否必选 0:非必选;1:必选
     */
    @Column(name = "is_required")
    private Byte isRequired;

    /**
     * 是否标准 0:非标准;1:标准
     */
    @Column(name = "is_standard")
    private Byte isStandard;

    /**
     * 是否删除 0:未删除;1:已删除
     */
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * 创建用户ID
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

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