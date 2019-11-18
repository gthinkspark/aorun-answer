package com.aorun.answer.dao;

import com.aorun.answer.model.QuestionBank;
import com.aorun.common.base.BaseMapper;

import java.util.List;
import java.util.Map;

public interface QuestionBankMapper extends BaseMapper<QuestionBank> {
    QuestionBank selectByPrimaryKey(Long id);

    List<QuestionBank> selectByMap(Map<String,Object> params);
}