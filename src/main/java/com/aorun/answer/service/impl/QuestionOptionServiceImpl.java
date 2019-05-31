package com.aorun.answer.service.impl;

import com.aorun.answer.dao.QuestionOptionMapper;
import com.aorun.answer.model.QuestionOption;
import com.aorun.answer.service.QuestionOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: QuestionOptionServiceImpl
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/10 15:51
 */
@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {
    @Autowired
    private QuestionOptionMapper questionOptionMapper;

    @Override
    public List<QuestionOption> getOptionByQuestionId(Long questionId) {
        return questionOptionMapper.selectByQuestionId(questionId);
    }

    @Override
    public QuestionOption getQuestionOptionById(Long id) {
        return questionOptionMapper.selectByPrimaryKey(id);
    }
}
