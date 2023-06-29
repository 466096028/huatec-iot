package com.huatec.iot.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "iot_categorys")
public class Categorys {
    /**
     * ID
     */
    @Id
    private Integer id;

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
     * 父ID
     */
    @Column(name = "super_id")
    private Integer superId;

    /**
     * 备注
     */
    private String remark;

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

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分类key
     *
     * @return category_key - 分类key
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置分类key
     *
     * @param categoryKey 分类key
     */
    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    /**
     * 获取分类名称
     *
     * @return category_name - 分类名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置分类名称
     *
     * @param categoryName 分类名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取父ID
     *
     * @return super_id - 父ID
     */
    public Integer getSuperId() {
        return superId;
    }

    /**
     * 设置父ID
     *
     * @param superId 父ID
     */
    public void setSuperId(Integer superId) {
        this.superId = superId;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取是否删除 0:未删除;1:已删除
     *
     * @return is_del - 是否删除 0:未删除;1:已删除
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置是否删除 0:未删除;1:已删除
     *
     * @param isDel 是否删除 0:未删除;1:已删除
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取创建用户ID
     *
     * @return create_user_id - 创建用户ID
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建用户ID
     *
     * @param createUserId 创建用户ID
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}