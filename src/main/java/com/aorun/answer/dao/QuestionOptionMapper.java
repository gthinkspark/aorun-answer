package com.aorun.answer.dao;

import com.aorun.answer.model.QuestionOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionOptionMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(QuestionOption record);
//
//    int insertSelective(QuestionOption record);
//
    QuestionOption selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(QuestionOption record);
//
//    int updateByPrimaryKey(QuestionOption record);

    List<QuestionOption> selectByQuestionId(@Param(value = "questionId") Long questionId);
}