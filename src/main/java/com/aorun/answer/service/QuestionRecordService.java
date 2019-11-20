package com.aorun.answer.service;

import com.aorun.answer.model.QuestionRecord;
import com.aorun.common.base.BasePageService;

import java.util.Map;

public interface QuestionRecordService extends BasePageService<QuestionRecord> {
    QuestionRecord getQuestionRecordByWorkerQuestionId(Long workerId,Long questionId,Long questionBankRecordId);

    Integer getTotalByMap(Map<String,Object> map);

    Integer getTotalDisWorkerTab(Map<String,Object> map);
}
