package com.aorun.answer.service.impl;

import com.aorun.answer.dao.QuestionOptionMapper;
import com.aorun.answer.model.QuestionOption;
import com.aorun.answer.service.QuestionOptionService;
import com.aorun.common.base.BasePageServiceImpl;
import com.aorun.common.util.ModuleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionOptionServiceImpl
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/10 15:51
 */
@Service
public class QuestionOptionServiceImpl extends BasePageServiceImpl<QuestionOptionMapper,QuestionOption> implements QuestionOptionService {
    @Autowired
    private QuestionOptionMapper questionOptionMapper;

    @Override
    protected void initMapper() {
        this.mapper = questionOptionMapper;
    }

    @Override
    public List<QuestionOption> getOptionByQuestionId(Long questionId) {
        Map<String,Object> map = new HashMap<>();
        map.put("isDelete", ModuleConstant.ENUM_N);
        map.put("questionId",questionId);
        return questionOptionMapper.selectByMap(map);
    }
}
