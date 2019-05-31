package com.aorun.answer.dto;

import com.aorun.answer.model.Question;
import com.aorun.answer.model.QuestionRecord;

import java.util.List;

/**
 * @ClassName: QuestionResultDto
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/13 17:50
 */
public class QuestionResultDto {
    private Long questionBankId;
    private int questionBankType;
    private int totalTime;
    private int rightQuantities;
    private int epoint;
    private int star;
    private String accuracy;
    private List<QuestionRecord> questionRecordList;

    public QuestionResultDto() {
    }

    public QuestionResultDto(Long questionBankId, int questionBankType, int totalTime, int rightQuantities, int epoint, int star, String accuracy) {
        this.questionBankId = questionBankId;
        this.questionBankType = questionBankType;
        this.totalTime = totalTime;
        this.rightQuantities = rightQuantities;
        this.epoint = epoint;
        this.star = star;
        this.accuracy = accuracy;
    }

    public List<QuestionRecord> getQuestionRecordList() {
        return questionRecordList;
    }

    public void setQuestionRecordList(List<QuestionRecord> questionRecordList) {
        this.questionRecordList = questionRecordList;
    }

    public Long getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(Long questionBankId) {
        this.questionBankId = questionBankId;
    }

    public int getQuestionBankType() {
        return questionBankType;
    }

    public void setQuestionBankType(int questionBankType) {
        this.questionBankType = questionBankType;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getRightQuantities() {
        return rightQuantities;
    }

    public void setRightQuantities(int rightQuantities) {
        this.rightQuantities = rightQuantities;
    }

    public int getEpoint() {
        return epoint;
    }

    public void setEpoint(int epoint) {
        this.epoint = epoint;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
}
