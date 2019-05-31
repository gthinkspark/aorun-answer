package com.aorun.answer.service.impl;

import com.aorun.answer.dao.QuestionRecordMapper;
import com.aorun.answer.model.QuestionRecord;
import com.aorun.answer.service.QuestionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: QuestionRecordServiceImpl
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/14 9:28
 */
@Service
public class QuestionRecordServiceImpl implements QuestionRecordService {
    @Autowired
    private QuestionRecordMapper questionRecordMapper;

    @Override
    public int insert(QuestionRecord questionRecord) {
        return questionRecordMapper.insert(questionRecord);
    }

    @Override
    public QuestionRecord getQuestionRecordByWorkerQuestionId(Long workerId,Long questionId,Long questionBankRecordId) {
        return questionRecordMapper.selectByWorkerQuestionid(workerId,questionId,questionBankRecordId);
    }
}
