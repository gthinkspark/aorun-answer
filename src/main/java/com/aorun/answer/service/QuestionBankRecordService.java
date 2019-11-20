package com.aorun.answer.service;

import com.aorun.answer.model.QuestionBankRecord;
import com.aorun.common.base.BasePageService;

import java.util.List;

public interface QuestionBankRecordService extends BasePageService<QuestionBankRecord> {
    int getToDayMaxStarByType(long workerId);

//    List<QuestionBankRecord> getQuestionBankRecordByPage(long workerId, int pageIndex,int pageSize);

//    List<QuestionBankRecord> getQuestionBankRecord(long workerId, int questionBankType);

    List<QuestionBankRecord> getQuestionBankRecordByWorker(long workerId);
//
    QuestionBankRecord getRecordLastStar(long workerId,Long questionBankId, int star);

    int getMaxStarByBankId(long workerId,Long questionBankId);
}
