package com.aorun.answer.service.impl;

import com.aorun.answer.dao.QuestionBankMapper;
import com.aorun.answer.model.QuestionBank;
import com.aorun.answer.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionBankServiceImpl
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/7 15:46
 *
 */
@Service
public class QuestionBankServiceImpl implements QuestionBankService {
    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Override
    public List<QuestionBank> getQuestionBankByType(int bankType, Date startTime, Date endTime) {
        Map<String,Object> params = new HashMap<>();
        params.put("bankType",bankType);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        return questionBankMapper.selectByMap(params);
    }

    @Override
    public List<QuestionBank> getQuestionBankByTypePage(int bankType, int pageIndex, int pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        Map<String,Object> params = new HashMap<>();
        params.put("bankType",bankType);
        params.put("start",start);
        params.put("limit",limit);
        return questionBankMapper.selectByMap(params);
    }

    @Override
    public QuestionBank getQuestionBankById(Long id) {
        return questionBankMapper.selectByPrimaryKey(id);
    }
}
