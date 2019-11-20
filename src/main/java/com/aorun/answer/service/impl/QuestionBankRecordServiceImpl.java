package com.aorun.answer.service.impl;

import com.aorun.answer.dao.QuestionBankRecordMapper;
import com.aorun.answer.model.QuestionBankRecord;
import com.aorun.answer.service.QuestionBankRecordService;
import com.aorun.answer.util.DateFormat;
import com.aorun.common.base.BasePageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class QuestionBankRecordServiceImpl extends BasePageServiceImpl<QuestionBankRecordMapper,QuestionBankRecord> implements QuestionBankRecordService {
    @Autowired
    private QuestionBankRecordMapper questionBankRecordMapper;

    @Override
    protected void initMapper() {
        this.mapper = questionBankRecordMapper;
    }

    @Override
    public QuestionBankRecord getRecordLastStar(long workerId, Long questionBankId, int star) {
        Map<String,Object> map = new HashMap<>();
        map.put("workerId",workerId);
        map.put("questionBankId",questionBankId);
        map.put("star",star);
        map.put("sortParamString","create_time desc");
        map.put("limit","1");
        List<QuestionBankRecord> questionBankRecords = questionBankRecordMapper.selectByMap(map);
        return null!=questionBankRecords&&questionBankRecords.size()>0?questionBankRecords.get(0):null;
    }

    @Override
    public int getToDayMaxStarByType(long workerId) {
        Map<String,Object> params = new HashMap<>();
        params.put("workerId",workerId);
        Date now = new Date();
        Date startOfDay = DateFormat.getStartTimeOfDay(now);
        Date endOfDay = DateFormat.getEndTimeOfDay(now);
        params.put("startTime",startOfDay);
        params.put("endTime",endOfDay);
        return questionBankRecordMapper.getMaxStarByMap(params);
    }

//    @Override
//    public List<QuestionBankRecord> getQuestionBankRecordByPage(long workerId,int pageIndex,int pageSize) {
//        ///** 启始页-位置 */
//        Integer start = (pageIndex - 1) * pageSize;
//        /** 每页大小  */
//        Integer limit = pageSize;
//        return questionBankRecordMapper.getQuestionBankRecordByPage(workerId,start,limit);
//    }

//    @Override
//    public List<QuestionBankRecord> getQuestionBankRecord(long workerId,int questionBankType) {
//        return questionBankRecordMapper.getQuestionBankRecord(workerId,questionBankType);
//    }

    @Override
    public List<QuestionBankRecord> getQuestionBankRecordByWorker(long workerId) {
        Map<String,Object> map = new HashMap<>();
        map.put("workerId",workerId);
        map.put("sortParamString","create_time desc");
        return questionBankRecordMapper.selectByMap(map);
    }

    @Override
    public int getMaxStarByBankId(long workerId, Long questionBankId) {
        Map<String,Object> params = new HashMap<>();
        params.put("workerId",workerId);
        params.put("questionBankId",questionBankId);
        return questionBankRecordMapper.getMaxStarByMap(params);
    }
}
