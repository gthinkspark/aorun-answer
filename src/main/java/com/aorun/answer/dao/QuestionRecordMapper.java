package com.aorun.answer.dao;

import com.aorun.answer.model.QuestionRecord;
import com.aorun.common.base.BaseMapper;

import java.util.Map;

public interface QuestionRecordMapper extends BaseMapper<QuestionRecord> {
    int getTotalDisWorkerTab(Map<String, Object> map);

//    QuestionRecord selectByWorkerQuestionid(@Param("workerId") Long workerId, @Param("questionId") Long questionId,@Param("questionBankRecordId")Long questionBankRecordId);
}