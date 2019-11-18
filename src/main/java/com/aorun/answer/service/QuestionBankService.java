package com.aorun.answer.service;

import com.aorun.answer.model.QuestionBank;
import com.aorun.common.base.BasePageService;

import java.util.Date;
import java.util.List;

public interface QuestionBankService extends BasePageService<QuestionBank> {
    List<QuestionBank> getQuestionBankByType(int bankType, Date startTime, Date endTime);

    List<QuestionBank> getQuestionBankByTypePage(int bankType, int pageIndex,int pageSize);

    QuestionBank getQuestionBankById(Long id);
}
