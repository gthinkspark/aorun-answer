package com.aorun.answer.service.impl;

import com.aorun.answer.dao.QuestionMapper;
import com.aorun.answer.model.Question;
import com.aorun.answer.service.QuestionService;
import com.aorun.common.base.BasePageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: QuestionServiceImpl
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/9 16:28
 */
@Service
public class QuestionServiceImpl extends BasePageServiceImpl<QuestionMapper,Question> implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    protected void initMapper() {
        this.mapper = questionMapper;
    }

    @Override
    public List<Question> selectByBankId(Long questionBankId) {
        return questionMapper.selectByBankId(questionBankId);
    }
}
