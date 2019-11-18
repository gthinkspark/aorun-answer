package com.aorun.answer.dao;

import com.aorun.answer.model.QuestionRecord;
import org.apache.ibatis.annotations.Param;

public interface QuestionRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionRecord record);

    int insertSelective(QuestionRecord record);

    QuestionRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionRecord record);

    int updateByPrimaryKey(QuestionRecord record);

    QuestionRecord selectByWorkerQuestionid(@Param("workerId") Long workerId, @Param("questionId") Long questionId,@Param("questionBankRecordId")Long questionBankRecordId);
}