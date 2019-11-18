package com.aorun.answer.dao;

import com.aorun.answer.model.Question;
import com.aorun.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper extends BaseMapper<Question> {
    List<Question> selectByBankId(@Param(value = "questionBankId") Long questionBankId);
}