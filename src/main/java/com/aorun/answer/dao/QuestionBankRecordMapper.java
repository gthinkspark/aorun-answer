package com.aorun.answer.dao;

import com.aorun.answer.model.QuestionBankRecord;
import com.aorun.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionBankRecordMapper extends BaseMapper<QuestionBankRecord> {

    int getMaxStarByMap(Map<String,Object> params);

    List<QuestionBankRecord> getQuestionBankRecordByPage(@Param("workerId") long workerId,@Param(value = "start")int start,@Param(value = "limit")int limit);

    List<QuestionBankRecord> getQuestionBankRecord(@Param("workerId") long workerId,@Param(value = "questionBankType")int questionBankType);

    List<QuestionBankRecord> getQuestionBankRecordByWorker(@Param("workerId") long workerId);

    QuestionBankRecord getRecordLastStar(@Param("workerId") long workerId,@Param(value = "questionBankId")long questionBankId,@Param("star")int star);
}