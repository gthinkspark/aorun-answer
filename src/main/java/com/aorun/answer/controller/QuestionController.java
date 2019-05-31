package com.aorun.answer.controller;

import com.aorun.EpointMsgDataStructure;
import com.aorun.answer.dto.QuestionResultDto;
import com.aorun.answer.dto.UserDto;
import com.aorun.answer.dto.WorkerMember;
import com.aorun.answer.model.*;
import com.aorun.answer.rabbitmq_direct.SenderEpointMsgDataStructure;
import com.aorun.answer.service.*;
import com.aorun.answer.util.CheckObjectIsNull;
import com.aorun.answer.util.biz.ImagePropertiesConfig;
import com.aorun.answer.util.biz.QuestionConstant;
import com.aorun.answer.util.biz.UnionUtil;
import com.aorun.answer.util.cache.redis.RedisCache;
import com.aorun.answer.util.jsonp.Jsonp;
import com.aorun.answer.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName: QuestionController
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/9 16:15
 */
@Controller
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionOptionService questionOptionService;
    @Autowired
    private QuestionBankRecordService questionBankRecordService;
    @Autowired
    private QuestionRecordService questionRecordService;
    @Autowired
    private QuestionBankService questionBankService;
    //积分消息
    @Autowired
    private SenderEpointMsgDataStructure senderEpointMsgDataStructure;

    /**
     * 开始答题接口
     * @param sid
     * @param questionBankId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "questionList",method = RequestMethod.GET)
    public Object questionList(
            @RequestParam(value = "sid",required = true)String sid,
            @RequestParam(value = "questionBankId",required = true)Long questionBankId){
        try {
            UserDto user = null;
            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) RedisCache.get(sid);
                if (CheckObjectIsNull.isNull(user)) {
                    return Jsonp.noLoginError("请先登录或重新登录");
                }
                workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    return Jsonp.noLoginError("授权已过期,重新授权");
                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }
            List<Question> questionList = questionService.selectByBankId(questionBankId);
            List<Map<String,Object>> questionMapList = new ArrayList<>();
            for(Question question:questionList){
                Map<String,Object> questionMap = new HashMap<>();
                questionMap.put("questionId",question.getId());
                questionMap.put("title",question.getTitle());
                questionMap.put("questionType",question.getQuestionType());
                questionMap.put("source",question.getSource());
                questionMap.put("detailType",question.getDetailType());
                questionMap.put("videoUrl", question.getVideoUrl());
                questionMap.put("questionAnswer",question.getQuestionAnswer());
                questionMap.put("questionAnswerOptionId",question.getQuestionAnswerOptionId());
                questionMap.put("answerKeys",question.getAnswerKeys());
                List<Map<String,Object>> optionMapList = new ArrayList<>();
                List<QuestionOption> optionByIdStr = questionOptionService.getOptionByQuestionId(question.getId());
                for(QuestionOption questionOption:optionByIdStr){
                    Map<String,Object> optionMap = new HashMap<>();
                    optionMap.put("optionName",questionOption.getContent());
                    optionMap.put("optionId",questionOption.getId());
                    optionMapList.add(optionMap);
                }
                questionMap.put("optionList",optionMapList);
                questionMapList.add(questionMap);
            }
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("questionList",questionMapList);
            return Jsonp_data.success(resultMap);
        }catch (Exception  e){
            e.printStackTrace();
            return Jsonp.error("服务异常");
        }
    }

    /**
     * 答题结果接口
     * @param sid
     * @param questionResultDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "questionResult",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Object questionResult(
            @RequestParam(value = "sid",required = true)String sid,
            @RequestBody QuestionResultDto questionResultDto){
        try {
            UserDto user = null;
            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) RedisCache.get(sid);
                if (CheckObjectIsNull.isNull(user)) {
                    return Jsonp.noLoginError("请先登录或重新登录");
                }
                workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    return Jsonp.noLoginError("授权已过期,重新授权");
                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }
            QuestionBankRecord questionBankRecord = new QuestionBankRecord();
            questionBankRecord.setAccuracy(questionResultDto.getAccuracy());
            questionBankRecord.setEpoint(questionResultDto.getEpoint());
            questionBankRecord.setQuestionBankId(questionResultDto.getQuestionBankId());
            questionBankRecord.setQuestionBankType(questionResultDto.getQuestionBankType());
            questionBankRecord.setRightQuantities(questionResultDto.getRightQuantities());
            questionBankRecord.setStar(questionResultDto.getStar());
            questionBankRecord.setTotalTime(questionResultDto.getTotalTime());
            questionBankRecord.setWorkerId(workerMember.getId());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            questionBankRecord.setCreateTime(calendar.getTime());
            questionBankRecord.setMonth(calendar.get(Calendar.MONTH)+1);
            questionBankRecordService.insert(questionBankRecord);
            List<QuestionRecord> questionRecordList = questionResultDto.getQuestionRecordList();
            for(QuestionRecord questionRecord:questionRecordList){
                questionRecord.setQuestionBankRecordId(questionBankRecord.getId());
                questionRecord.setCreateTime(new Date());
                questionRecord.setWorkerId(workerMember.getId());
                questionRecord.setQuestionBankId(questionResultDto.getQuestionBankId());
                questionRecordService.insert(questionRecord);
            }
            if(questionBankRecord.getEpoint()>0){
                //积分消息
                EpointMsgDataStructure epointMsgDataStructure = new EpointMsgDataStructure();
                epointMsgDataStructure.setBizUniqueSignCode("questionBankRecordId:"+questionBankRecord.getId());
                epointMsgDataStructure.setEpoint(questionBankRecord.getEpoint());
                epointMsgDataStructure.setMsgId(UUID.randomUUID().toString());
                epointMsgDataStructure.setWorkerId(questionBankRecord.getWorkerId());
                if(questionResultDto.getQuestionBankType()==QuestionConstant.QUESTION_BANKTYPE_WEEK){
                    epointMsgDataStructure.setEpointConfigCode(QuestionConstant.MQ_QUESTION_EPOINT_KEY);
                }else{
                    epointMsgDataStructure.setEpointConfigCode(QuestionConstant.MQ_EXAM_EPOINT_KEY);
                }
                senderEpointMsgDataStructure.sendObject(epointMsgDataStructure);
            }

            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("questionBankRecordId",questionBankRecord.getId());
            resultMap.put("questionBankId",questionBankRecord.getQuestionBankId());
            return Jsonp_data.success(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return Jsonp.error();
        }
    }

    /**
     * 获取成绩接口
     * @param sid
     * @param questionBankRecordId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getQuestionResult")
    public Object questionResult(
            @RequestParam(value = "sid",required = true)String sid,
            @RequestParam(value = "questionBankRecordId",required = true)Long questionBankRecordId){
        try {
            UserDto user = null;
            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) RedisCache.get(sid);
                if (CheckObjectIsNull.isNull(user)) {
                    return Jsonp.noLoginError("请先登录或重新登录");
                }
                workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    return Jsonp.noLoginError("授权已过期,重新授权");
                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }
            QuestionBankRecord questionBankRecordById = questionBankRecordService.getQuestionBankRecordById(questionBankRecordId);
            if(null==questionBankRecordById||questionBankRecordById.getWorkerId()!=workerMember.getId()){
                return Jsonp.paramError("答题记录不存在");
            }
            QuestionBank questionBankById = questionBankService.getQuestionBankById(questionBankRecordById.getQuestionBankId());
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("questionBankTitle",questionBankById.getTitle());
            resultMap.put("totalTime",questionBankRecordById.getTotalTime());
            resultMap.put("star",questionBankRecordById.getStar());
            resultMap.put("accuracy",questionBankRecordById.getAccuracy());
            resultMap.put("epoint",questionBankRecordById.getEpoint());
            resultMap.put("questionBankId",questionBankById.getId());
            resultMap.put("questionBankRecordId",questionBankRecordId);
            return Jsonp_data.success(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return Jsonp.error("数据异常");
        }
    }

    /**
     * 答案解析接口
     * @param sid
     * @param questionBankId
     * @param questionBankRecordId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "questionAnswerKeys",method = RequestMethod.GET)
    public Object questionAnswerKeys(
            @RequestParam(value = "sid",required = true)String sid,
            @RequestParam(value = "questionBankId",required = true)Long questionBankId,
            @RequestParam(value = "questionBankRecordId",required = true)Long questionBankRecordId){
        try {
            UserDto user = null;
            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) RedisCache.get(sid);
                if (CheckObjectIsNull.isNull(user)) {
                    return Jsonp.noLoginError("请先登录或重新登录");
                }
                workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    return Jsonp.noLoginError("授权已过期,重新授权");
                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }
            Map<String,Object> resultMap = new HashMap<>();
            //题库标题
            QuestionBank questionBankById = questionBankService.getQuestionBankById(questionBankId);
            resultMap.put("questionBankTitle",questionBankById.getTitle());
            //总共时间
            QuestionBankRecord questionBankRecordById = questionBankRecordService.getQuestionBankRecordById(questionBankRecordId);
            resultMap.put("toatleTime",questionBankRecordById.getTotalTime());
            //查询这个题目库的题目
            List<Question> questionList = questionService.selectByBankId(questionBankId);
            List<Map<String,Object>> questionMapList = new ArrayList<>();
            for(Question question:questionList){
                Map<String,Object> questionMap = new HashMap<>();
                questionMap.put("title",question.getTitle());
                questionMap.put("questionType",question.getQuestionType());
                questionMap.put("source",question.getSource());
                questionMap.put("detailType",question.getDetailType());
                questionMap.put("videoUrl",question.getVideoUrl());
                questionMap.put("questionAnswer",question.getQuestionAnswer());
                questionMap.put("answerKeys",question.getAnswerKeys());
                List<String> answerStringList = new ArrayList<>();
                if(!StringUtils.isEmpty(question.getQuestionAnswerOptionId())){
                    //获取每个题目的答案选项
                    String[] split = question.getQuestionAnswerOptionId().split(",");
                    for(String optionId:split){
                        QuestionOption answerById = questionOptionService.getQuestionOptionById(Long.parseLong(optionId));
                        answerStringList.add(answerById.getContent());
                    }
                }
                //正确答案集合
                questionMap.put("rightAnswerList",answerStringList);
                //用户做答的答案
                questionMap.put("wokerAnswerStr","");
                questionMap.put("isRight","n");
                QuestionRecord questionRecordByWorkerQuestionId = questionRecordService.getQuestionRecordByWorkerQuestionId(workerMember.getId(), question.getId(), questionBankRecordId);
                if(null!=questionRecordByWorkerQuestionId){
                    questionMap.put("wokerAnswerStr",questionRecordByWorkerQuestionId.getAnswer());
                    questionMap.put("isRight",questionRecordByWorkerQuestionId.getIsRight());
                }
                questionMapList.add(questionMap);
            }
            resultMap.put("questionList",questionMapList);
            return Jsonp_data.success(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return Jsonp.error();
        }

    }

}
