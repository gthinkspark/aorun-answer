package com.aorun.answer.model;

import java.util.Date;

public class QuestionRecord {
    private Long id;

    private Long questionBankRecordId;

    private Long questionId;

    private String answer;

    private String isRight;

    private Date createTime;

    private Long workerId;

    private Long questionBankId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionBankRecordId() {
        return questionBankRecordId;
    }

    public void setQuestionBankRecordId(Long questionBankRecordId) {
        this.questionBankRecordId = questionBankRecordId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight == null ? null : isRight.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}