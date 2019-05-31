package com.aorun.answer.model;

import java.util.Date;

public class Question {
    private Long id;

    private Long questionBankId;

    private String title;

    private String source;

    private Integer questionType;

    private Integer detailType;

    private Integer answerNumber;

    private String videoUrl;

    private Date createTime;

    private String isDelete;

    private String questionAnswer;

    private String questionAnswerOptionId;

    private String answerKeys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(Long questionBankId) {
        this.questionBankId = questionBankId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getDetailType() {
        return detailType;
    }

    public void setDetailType(Integer detailType) {
        this.detailType = detailType;
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer == null ? null : questionAnswer.trim();
    }

    public String getQuestionAnswerOptionId() {
        return questionAnswerOptionId;
    }

    public void setQuestionAnswerOptionId(String questionAnswerOptionId) {
        this.questionAnswerOptionId = questionAnswerOptionId == null ? null : questionAnswerOptionId.trim();
    }

    public String getAnswerKeys() {
        return answerKeys;
    }

    public void setAnswerKeys(String answerKeys) {
        this.answerKeys = answerKeys == null ? null : answerKeys.trim();
    }
}