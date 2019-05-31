package com.aorun.answer.dao;

import com.aorun.answer.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(Question record);
//
//    int insertSelective(Question record);
//
//    Question selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(Question record);
//
//    int updateByPrimaryKeyWithBLOBs(Question record);
//
//    int updateByPrimaryKey(Question record);

    List<Question> selectByBankId(@Param(value = "questionBankId") Long questionBankId);

}