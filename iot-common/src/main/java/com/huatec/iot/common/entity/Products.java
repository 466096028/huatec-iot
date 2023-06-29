package com.huatec.iot.common.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Table(name = "iot_products")
@ToString
public class Products {
    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC", strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "id")
    private Integer id;

    /**
     * 产品key
     */
    @Column(name = "product_key")
    private String productKey;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 分类Id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 分类键
     */
    @Column(name = "category_key")
    private String categoryKey;

    /**
     * 分类名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 节点类型
     */
    @Column(name = "node_type")
    private Byte nodeType;

    /**
     * 连网方式
     */
    @Column(name = "net_type")
    private Byte netType;

    /**
     * 数据格式
     */
    @Column(name = "data_format")
    private Byte dataFormat;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否系统：0为否，1为是
     */
    @Column(name = "is_system")
    private Byte isSystem;

    /**
     * 产品状态
     */
    @Column(name = "status")
    private Byte status;

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
     * 获取产品key
     *
     * @return product_key - 产品key
     */
    public String getProductKey() {
        return productKey;
    }

    /**
     * 设置产品key
     *
     * @param productKey 产品key
     */
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取分类Id
     *
     * @return category_id - 分类Id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类Id
     *
     * @param categoryId 分类Id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取分类键
     *
     * @return category_key - 分类键
     */
    public String getCategoryKey() {
        return categoryKey;
    }

    /**
     * 设置分类键
     *
     * @param categoryKey 分类键
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
     * 获取节点类型
     *
     * @return node_type - 节点类型
     */
    public Byte getNodeType() {
        return nodeType;
    }

    /**
     * 设置节点类型
     *
     * @param nodeType 节点类型
     */
    public void setNodeType(Byte nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 获取连网方式
     *
     * @return net_type - 连网方式
     */
    public Byte getNetType() {
        return netType;
    }

    /**
     * 设置连网方式
     *
     * @param netType 连网方式
     */
    public void setNetType(Byte netType) {
        this.netType = netType;
    }

    /**
     * 获取数据格式
     *
     * @return data_format - 数据格式
     */
    public Byte getDataFormat() {
        return dataFormat;
    }

    /**
     * 设置数据格式
     *
     * @param dataFormat 数据格式
     */
    public void setDataFormat(Byte dataFormat) {
        this.dataFormat = dataFormat;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取产品状态
     *
     * @return status - 产品状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置产品状态
     *
     * @param status 产品状态
     */
    public void setStatus(Byte status) {
        this.status = status;
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

    public Byte getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Byte isSystem) {
        this.isSystem = isSystem;
    }
}