package com.aorun.answer.service;

import com.aorun.answer.model.Question;
import com.aorun.common.base.BasePageService;

import java.util.List;

public interface QuestionService extends BasePageService<Question> {

    List<Question> selectByBankId(Long questionBankId);

}
