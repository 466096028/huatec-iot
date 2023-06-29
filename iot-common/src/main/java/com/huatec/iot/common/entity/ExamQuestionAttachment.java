package com.huatec.iot.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "iot_exam_question_attachment")
public class ExamQuestionAttachment {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 主键
     */
    @Column(name = "question_id")
    private String questionId;

    /**
     * 附件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 附件网址
     */
    @Column(name = "file_url")
    private String fileUrl;

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
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

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
     * 获取附件名称
     *
     * @return file_name - 附件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置附件名称
     *
     * @param fileName 附件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取附件网址
     *
     * @return file_url - 附件网址
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 设置附件网址
     *
     * @param fileUrl 附件网址
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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