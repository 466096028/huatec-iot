package com.huatec.iot.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "iot_exam_question")
public class ExamQuestion {
    /**
     * 主键
     */
    @Id
    @Column(name = "question_id")
    private String questionId;

    /**
     * 0:选择；1：操作
     */
    private Short type;

    /**
     * 题目名称
     */
    private String name;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 创建人标识
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建人code
     */
    @Column(name = "create_user_code")
    private String createUserCode;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人标识
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新人code
     */
    @Column(name = "update_user_code")
    private String updateUserCode;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_del")
    private Short isDel;

    /**
     * 获取主键
     *
     * @return question_id - 主键
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置主键
     *
     * @param questionId 主键
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取1:选择；2：操作
     *
     * @return type - 1:选择；2：操作
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置1:选择；2：操作
     *
     * @param type 1:选择；2：操作
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 获取题目名称
     *
     * @return name - 题目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置题目名称
     *
     * @param name 题目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取题目内容
     *
     * @return content - 题目内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置题目内容
     *
     * @param content 题目内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取分数
     *
     * @return score - 分数
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置分数
     *
     * @param score 分数
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取创建人标识
     *
     * @return create_user - 创建人标识
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人标识
     *
     * @param createUser 创建人标识
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建人code
     *
     * @return create_user_code - 创建人code
     */
    public String getCreateUserCode() {
        return createUserCode;
    }

    /**
     * 设置创建人code
     *
     * @param createUserCode 创建人code
     */
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
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
     * 获取更新人标识
     *
     * @return update_user - 更新人标识
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人标识
     *
     * @param updateUser 更新人标识
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取更新人code
     *
     * @return update_user_code - 更新人code
     */
    public String getUpdateUserCode() {
        return updateUserCode;
    }

    /**
     * 设置更新人code
     *
     * @param updateUserCode 更新人code
     */
    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return is_del
     */
    public Short getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }
}