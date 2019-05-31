package com.aorun.answer.service;

import com.aorun.answer.model.QuestionOption;

import java.util.List;

public interface QuestionOptionService {
    List<QuestionOption> getOptionByQuestionId(Long questionId);

    QuestionOption getQuestionOptionById(Long id);
}
