package com.aorun.answer.service;

import com.aorun.answer.model.QuestionRecord;

import java.util.List;

public interface QuestionRecordService {
    int insert(QuestionRecord questionRecord);

    QuestionRecord getQuestionRecordByWorkerQuestionId(Long workerId,Long questionId,Long questionBankRecordId);
}
