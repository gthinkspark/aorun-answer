package com.aorun.answer.service.impl;

import com.aorun.answer.dao.QuestionBankRecordMapper;
import com.aorun.answer.model.QuestionBankRecord;
import com.aorun.answer.service.QuestionBankRecordService;
import com.aorun.answer.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
/**
 * @ClassName: QuestionBankRecordServiceImpl
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/7 16:12
 *
 */
@Service
public class QuestionBankRecordServiceImpl implements QuestionBankRecordService {
    @Autowired
    private QuestionBankRecordMapper questionBankRecordMapper;

    @Override
    public QuestionBankRecord getRecordLastStar(long workerId, Long questionBankId, int star) {
        return questionBankRecordMapper.getRecordLastStar(workerId,questionBankId,star);
    }

    @Override
    public int getToDayMaxStarByType(long workerId, int questionBankType) {
        Map<String,Object> params = new HashMap<>();
        params.put("workerId",workerId);
        params.put("questionBankType",questionBankType);
        Date now = new Date();
        Date startOfDay = DateFormat.getStartTimeOfDay(now);
        Date endOfDay = DateFormat.getEndTimeOfDay(now);
        params.put("startTime",startOfDay);
        params.put("endTime",endOfDay);
        return questionBankRecordMapper.getMaxStarByMap(params);
    }

    @Override
    public int insert(QuestionBankRecord questionBankRecord) {
        return questionBankRecordMapper.insert(questionBankRecord);
    }

    @Override
    public List<QuestionBankRecord> getQuestionBankRecordByPage(long workerId,int pageIndex,int pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return questionBankRecordMapper.getQuestionBankRecordByPage(workerId,start,limit);
    }

    @Override
    public List<QuestionBankRecord> getQuestionBankRecord(long workerId,int questionBankType) {
        return questionBankRecordMapper.getQuestionBankRecord(workerId,questionBankType);
    }

    @Override
    public int getMaxStarByBankId(long workerId, Long questionBankId) {
        Map<String,Object> params = new HashMap<>();
        params.put("workerId",workerId);
        params.put("questionBankId",questionBankId);
        return questionBankRecordMapper.getMaxStarByMap(params);
    }

    @Override
    public QuestionBankRecord getQuestionBankRecordById(Long id) {
        return questionBankRecordMapper.selectByPrimaryKey(id);
    }
}
