package com.huatec.iot.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "iot_exam_question_paper")
public class ExamQuestionPaper {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 试卷ID
     */
    @Column(name = "paper_id")
    private String paperId;

    /**
     * 试题ID
     */
    @Column(name = "question_id")
    private String questionId;

    /**
     * 试题顺序
     */
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
     * 获取试卷ID
     *
     * @return paper_id - 试卷ID
     */
    public String getPaperId() {
        return paperId;
    }

    /**
     * 设置试卷ID
     *
     * @param paperId 试卷ID
     */
    public void setPaperId(String paperId) {
        this.paperId = paperId;
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
     * 获取试题顺序
     *
     * @return sort - 试题顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置试题顺序
     *
     * @param sort 试题顺序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}