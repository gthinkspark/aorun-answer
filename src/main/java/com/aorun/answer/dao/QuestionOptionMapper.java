package com.aorun.answer.dao;

import com.aorun.answer.model.QuestionOption;
import com.aorun.common.base.BaseMapper;

public interface QuestionOptionMapper extends BaseMapper<QuestionOption> {
    QuestionOption selectByPrimaryKey(Long id);
}