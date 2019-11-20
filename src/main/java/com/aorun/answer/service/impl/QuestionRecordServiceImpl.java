package com.aorun.answer.service.impl;

import com.aorun.answer.dao.QuestionRecordMapper;
import com.aorun.answer.model.QuestionRecord;
import com.aorun.answer.service.QuestionRecordService;
import com.aorun.common.base.BasePageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionRecordServiceImpl
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/14 9:28
 */
@Service
public class QuestionRecordServiceImpl extends BasePageServiceImpl<QuestionRecordMapper, QuestionRecord> implements QuestionRecordService {
    @Autowired
    private QuestionRecordMapper questionRecordMapper;

    @Override
    protected void initMapper() {
        this.mapper = questionRecordMapper;
    }

    @Override
    public Integer getTotalByMap(Map<String, Object> map) {
        return questionRecordMapper.getTotal(map);
    }

    @Override
    public QuestionRecord getQuestionRecordByWorkerQuestionId(Long workerId,Long questionId,Long questionBankRecordId) {
        Map<String,Object> map = new HashMap<>();
        map.put("workerId",workerId);
        map.put("questionId",questionId);
        map.put("questionBankRecordId",questionBankRecordId);
        map.put("limit",1);
        List<QuestionRecord> questionRecords = questionRecordMapper.selectByMap(map);
        return null!=questionRecords&&questionRecords.size()>0?questionRecords.get(0):null;
    }

    @Override
    public Integer getTotalDisWorkerTab(Map<String, Object> map) {
        return questionRecordMapper.getTotalDisWorkerTab(map);
    }
}
