package com.aorun.answer.dao;

import com.aorun.answer.model.QuestionBank;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionBankMapper {
    QuestionBank selectByPrimaryKey(Long id);

    List<QuestionBank> selectByMap(Map<String,Object> params);

//    List<QuestionBank> selectByTypeMonth(@Param("type") Integer type,@Param("month") Integer month);
}