package com.aorun.answer.app;

import com.aorun.answer.dto.UserDto;
import com.aorun.answer.dto.WorkerMember;
import com.aorun.answer.model.QuestionBank;
import com.aorun.answer.model.QuestionBankRecord;
import com.aorun.answer.service.QuestionBankRecordService;
import com.aorun.answer.service.QuestionBankService;
import com.aorun.answer.util.CheckObjectIsNull;
import com.aorun.answer.util.DateFormat;
import com.aorun.answer.util.QuestionConstant;
import com.aorun.answer.util.biz.UnionUtil;
import com.aorun.common.annotation.ApiVersion;
import com.aorun.common.util.RedisUtil;
import com.aorun.common.util.jsonp.Jsonp;
import com.aorun.common.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @ClassName: QuestionController
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/7 16:32
 */
@Controller
@ApiVersion(1)
@RequestMapping("app/{version}/questionBank")
public class QuestionBankController {
    @Autowired
    private QuestionBankService questionBankService;
    @Autowired
    private QuestionBankRecordService questionBankRecordService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 每周一答首页
     * @param sid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "questionHome",method = RequestMethod.GET)
    public Object questionHome( @RequestParam(name = "sid", required = true) String sid){
        try {
            UserDto user = null;
//            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) redisUtil.getObj(sid,UserDto.class);
                if (CheckObjectIsNull.isNull(user)) {
                    return Jsonp.noLoginError("请先登录或重新登录");
                }
//                workerMember = redisUtil.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
//                if (CheckObjectIsNull.isNull(workerMember)) {
//                    return Jsonp.noLoginError("授权已过期,重新授权");
//                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }
            Map<String,Object> resultMap = new HashMap<>();
            List<Map<String,Object>> dataList = new ArrayList<>();
            //查询1个月的题目
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateFormat.getStartTimeOfDay(now));
            //1个月第一天
            calendar.set(Calendar.DAY_OF_MONTH,1);
            Date startTime = calendar.getTime();
            calendar.setTime(DateFormat.getEndTimeOfDay(now));
            //1个月最后一天
            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date endTime = calendar.getTime();
            List<QuestionBank> questionBankByType = questionBankService.getQuestionBankByType(QuestionConstant.QUESTION_BANKTYPE_WEEK,startTime,endTime);

            for(QuestionBank questionBank:questionBankByType){
                Map<String,Object> dataMap = new HashMap<>();
                dataMap.put("title",questionBank.getTitle());
                dataMap.put("id",questionBank.getId());
                dataMap.put("isAnswer","n");
                dataMap.put("star",0);
                int maxStarByBankId = questionBankRecordService.getMaxStarByBankId(user.getMemberId(), questionBank.getId());
                if(maxStarByBankId>=0){
                    dataMap.put("isAnswer","y");
                    dataMap.put("star",maxStarByBankId);
                }
                dataList.add(dataMap);
            }
            List<QuestionBankRecord> questionBankRecordList = questionBankRecordService.getQuestionBankRecordByWorker(user.getMemberId());
            int sumEpoint=0;
            for(QuestionBankRecord bankRecord:questionBankRecordList){
                sumEpoint+=bankRecord.getEpoint();
            }
            resultMap.put("todayStar",questionBankRecordService.getToDayMaxStarByType(user.getMemberId()));
            resultMap.put("answerCount",questionBankRecordList.size());
            resultMap.put("sumEpoint",sumEpoint);
            resultMap.put("questionBankList",dataList);
            return Jsonp_data.success(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return Jsonp.error("查询异常");
        }

    }

    /**
     * 每周一答更多答题页面
     * @param sid
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "questionList",method = RequestMethod.GET)
    public Object questionList( @RequestParam(name = "sid", required = true) String sid,
                                    @RequestParam(name="pageIndex", required = true, defaultValue = "1") Integer pageIndex,
                                    @RequestParam(name="pageSize", defaultValue = "2") Integer pageSize){
        try {
            UserDto user = null;
            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) redisUtil.get(sid);
                if (CheckObjectIsNull.isNull(user)) {
                    return Jsonp.noLoginError("请先登录或重新登录");
                }
                workerMember = redisUtil.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    return Jsonp.noLoginError("授权已过期,重新授权");
                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }
            Map<String,Object> resultMap = new HashMap<>();

            List<Map<String,Object>> questionBankWeekMapList = new ArrayList<>();
            //查询1个月的题目
            Date now = new Date();
            Calendar calender = Calendar.getInstance();
            calender.setTime(now);
            //当前时间的年、月
            int curYear = calender.get(Calendar.YEAR);
            int curMonth = calender.get(Calendar.MONTH)+1;
            //设置分页时间
            calender.add(Calendar.MONTH,-(pageIndex-1)*pageSize);
            for(int i=0;i<pageSize;i++){
                //1天的最早时间
                calender.setTime(DateFormat.getStartTimeOfDay(calender.getTime()));
                //1个月第一天
                calender.set(Calendar.DAY_OF_MONTH,1);
                //开始时间
                Date startTime = calender.getTime();
                //1天的最晚时间
                calender.setTime(DateFormat.getEndTimeOfDay(calender.getTime()));
                //1个月最后一天
                calender.set(Calendar.DAY_OF_MONTH,calender.getActualMaximum(Calendar.DAY_OF_MONTH));
                //结束时间
                Date endTime = calender.getTime();
                //查询数据
                List<Map<String,Object>> dataList = new ArrayList<>();
                Map<String,Object> monthQuestionMap = new HashMap<>();
                List<QuestionBank> questionBankByTypeMonth = questionBankService.getQuestionBankByType(QuestionConstant.QUESTION_BANKTYPE_WEEK,startTime,endTime);
                for(QuestionBank questionBank:questionBankByTypeMonth){
                    Map<String,Object> dataMap = new HashMap<>();
                    dataMap.put("title",questionBank.getTitle());
                    dataMap.put("id",questionBank.getId());
                    dataMap.put("isAnswer","n");
                    dataMap.put("star",0);
                    int maxStarByBankId = questionBankRecordService.getMaxStarByBankId(workerMember.getId(), questionBank.getId());
                    if(maxStarByBankId>=0){
                        dataMap.put("isAnswer","y");
                        dataMap.put("star",maxStarByBankId);
                    }
                    dataList.add(dataMap);
                }
                int month = calender.get(Calendar.MONTH)+1;
                int year = calender.get(Calendar.YEAR);
                monthQuestionMap.put("questionBankList",dataList);
                monthQuestionMap.put("month",month+"月");
                if(year==curYear&&month==curMonth){
                    monthQuestionMap.put("month","本月");
                }else if(year!=curYear){
                    monthQuestionMap.put("month",year+"年"+month+"月");
                }
                questionBankWeekMapList.add(monthQuestionMap);
                //递减查询的月份
                calender.add(Calendar.MONTH,-1);
            }
            resultMap.put("questionBankWeekList",questionBankWeekMapList);
            return Jsonp_data.success(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return Jsonp.error("查询异常");
        }
    }

    /**
     * 专题考试列表
     * @param sid
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "questionExamList",method = RequestMethod.GET)
    public Object questionExamList( @RequestParam(name = "sid", required = true) String sid,
                                    @RequestParam(name="pageIndex", required = true, defaultValue = "1") Integer pageIndex,
                                    @RequestParam(name="pageSize", defaultValue = "20") Integer pageSize){
        try {
            UserDto user = null;
            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) redisUtil.get(sid);
                if (CheckObjectIsNull.isNull(user)) {
                    return Jsonp.noLoginError("请先登录或重新登录");
                }
                workerMember = redisUtil.getObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
                if (CheckObjectIsNull.isNull(workerMember)) {
                    return Jsonp.noLoginError("授权已过期,重新授权");
                }
            } else {
                return Jsonp.noLoginError("用户SID不正确,请核对后重试");
            }
            Map<String,Object> resultMap = new HashMap<>();
            List<Map<String,Object>> questionBankExamMapList = new ArrayList<>();
            List<QuestionBank> questionBankByTypePage = questionBankService.getQuestionBankByTypePage(QuestionConstant.QUESTION_BANKTYPE_EXAM, pageIndex, pageSize);
            for(QuestionBank questionBank:questionBankByTypePage){
                Map<String,Object> dataMap = new HashMap<>();
                dataMap.put("id",questionBank.getId());
                dataMap.put("title",questionBank.getTitle());
                dataMap.put("limitTime",questionBank.getLimitTime());
                dataMap.put("questionNumber",questionBank.getLimitNum());
                dataMap.put("startTime",questionBank.getStartTime().getTime());
                dataMap.put("endTime",questionBank.getEndTime().getTime());
                dataMap.put("isAnswer","n");
                dataMap.put("star",0);
                int maxStarByBankId = questionBankRecordService.getMaxStarByBankId(workerMember.getId(), questionBank.getId());
                if(maxStarByBankId>=0){
                    dataMap.put("isAnswer","y");
                    dataMap.put("star",maxStarByBankId);
                    QuestionBankRecord recordLastStar = questionBankRecordService.getRecordLastStar(workerMember.getId(), questionBank.getId(), maxStarByBankId);
                    dataMap.put("questionBankRecordId",recordLastStar.getId());
                }
                questionBankExamMapList.add(dataMap);
            }
            resultMap.put("questionBankExamList",questionBankExamMapList);
            return Jsonp_data.success(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return Jsonp.error("查询异常");
        }
    }
}
