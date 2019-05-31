package com.aorun.answer.model;

import java.util.Date;

public class QuestionBankRecord {
    private Long id;

    private Long workerId;

    private Long questionBankId;

    private Integer questionBankType;

    private Integer totalTime;

    private Integer rightQuantities;

    private Integer epoint;

    private String accuracy;

    private Integer star;

    private Date createTime;

    private Integer month;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(Long questionBankId) {
        this.questionBankId = questionBankId;
    }

    public Integer getQuestionBankType() {
        return questionBankType;
    }

    public void setQuestionBankType(Integer questionBankType) {
        this.questionBankType = questionBankType;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getRightQuantities() {
        return rightQuantities;
    }

    public void setRightQuantities(Integer rightQuantities) {
        this.rightQuantities = rightQuantities;
    }

    public Integer getEpoint() {
        return epoint;
    }

    public void setEpoint(Integer epoint) {
        this.epoint = epoint;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy == null ? null : accuracy.trim();
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}