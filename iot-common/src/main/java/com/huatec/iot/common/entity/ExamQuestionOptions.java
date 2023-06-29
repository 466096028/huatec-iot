package com.huatec.iot.common.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "iot_exam_question_options")
public class ExamQuestionOptions {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 试题ID
     */
    @Column(name = "question_id")
    private String questionId;

    /**
     * 选项内容
     */
    private String content;

    /**
     * 是否为正确选项
     */
    @Column(name = "is_correct")
    private Integer isCorrect;

    @Column(name = "is_del")
    private Short isDel;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

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
     * 获取试题ID
     *
     * @return question_id - 试题ID
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置试题ID
     *
     * @param questionId 试题ID
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取选项内容
     *
     * @return content - 选项内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置选项内容
     *
     * @param content 选项内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取是否为正确选项
     *
     * @return is_correct - 是否为正确选项
     */
    public Integer getIsCorrect() {
        return isCorrect;
    }

    /**
     * 设置是否为正确选项
     *
     * @param isCorrect 是否为正确选项
     */
    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

    /**
     * @return is_del
     */
    public Short getIsDel() {
        return isDel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }


}