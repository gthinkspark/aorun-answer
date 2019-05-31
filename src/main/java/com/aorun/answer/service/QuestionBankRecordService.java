package com.aorun.answer.service;

import com.aorun.answer.model.QuestionBankRecord;

import java.util.List;

public interface QuestionBankRecordService {
    int getToDayMaxStarByType(long workerId,int questionBankType);

    List<QuestionBankRecord> getQuestionBankRecordByPage(long workerId, int pageIndex,int pageSize);

    List<QuestionBankRecord> getQuestionBankRecord(long workerId, int questionBankType);

    QuestionBankRecord getRecordLastStar(long workerId,Long questionBankId, int star);

    QuestionBankRecord getQuestionBankRecordById(Long id);

    int getMaxStarByBankId(long workerId,Long questionBankId);

    int insert(QuestionBankRecord questionBankRecord);
}
