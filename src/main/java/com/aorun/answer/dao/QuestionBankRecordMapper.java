package com.aorun.answer.dao;

import com.aorun.answer.model.QuestionBankRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionBankRecordMapper {
    QuestionBankRecord selectByPrimaryKey(Long id);

//    int getToDayMaxStarByType(@Param("workerId") long workerId,@Param("questionBankType") int questionBankType);
//
//    int getMaxStarByBankId(@Param("workerId") long workerId,@Param("questionBankId") Long questionBankId);

    int insert(QuestionBankRecord record);

    int getMaxStarByMap(Map<String,Object> params);

    List<QuestionBankRecord> getQuestionBankRecordByPage(@Param("workerId") long workerId,@Param(value = "start")int start,@Param(value = "limit")int limit);

    List<QuestionBankRecord> getQuestionBankRecord(@Param("workerId") long workerId,@Param(value = "questionBankType")int questionBankType);

    QuestionBankRecord getRecordLastStar(@Param("workerId") long workerId,@Param(value = "questionBankId")long questionBankId,@Param("star")int star);
}