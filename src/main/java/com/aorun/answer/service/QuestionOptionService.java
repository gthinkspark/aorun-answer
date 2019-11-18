package com.aorun.answer.service;

import com.aorun.answer.model.QuestionOption;
import com.aorun.common.base.BasePageService;

import java.util.List;

public interface QuestionOptionService extends BasePageService<QuestionOption> {
    List<QuestionOption> getOptionByQuestionId(Long questionId);
}
