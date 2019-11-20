package com.aorun.answer.screen;

import com.aorun.answer.model.Question;
import com.aorun.answer.model.QuestionBank;
import com.aorun.answer.model.QuestionOption;
import com.aorun.answer.service.QuestionBankService;
import com.aorun.answer.service.QuestionOptionService;
import com.aorun.answer.service.QuestionRecordService;
import com.aorun.answer.service.QuestionService;
import com.aorun.common.annotation.ApiVersion;
import com.aorun.common.base.BasePagination;
import com.aorun.common.util.ModuleConstant;
import com.aorun.common.util.jsonp.Jsonp;
import com.aorun.common.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ClassName: QuestionController
 * @Description: TODO
 * @author: lg
 * @date: 2019/11/18 16:08
 */
@RestController
@ApiVersion(1)
@RequestMapping("{version}/screen/question")
public class ScrQuestionService {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionOptionService questionOptionService;
    @Autowired
    private QuestionBankService questionBankService;
    @Autowired
    private QuestionRecordService questionRecordService;
    /**
     * 开始答题接口
     * @return
     */
    @RequestMapping(value = "questionList",method = RequestMethod.GET)
    public Object questionHomeList(){
        try {
            BasePagination<QuestionBank> page = new BasePagination<>();
            Map<String,Object> params = new HashMap<>();
            params.put("isDelete", ModuleConstant.ENUM_N);
            params.put("status",1); //启用
            page.setParams(params);
            page.setLimit(1);   //查询最新的一个库
            List<QuestionBank> questionBankList = questionBankService.findByPage(page).getResult();
            List<Map<String,Object>> questionMapList = new ArrayList<>();
            if(null!=questionBankList&&questionBankList.size()>0){
                List<Question> questionList = questionService.selectByBankId(questionBankList.get(0).getId());
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
                    List<String> answerOptionIdArray = Arrays.asList(question.getQuestionAnswerOptionId().split(","));
                    StringBuffer questionAnswerOptionStr = new StringBuffer();
                    List<Map<String,Object>> optionMapList = new ArrayList<>();
                    List<QuestionOption> optionByIdStr = questionOptionService.getOptionByQuestionId(question.getId());
                    for(QuestionOption questionOption:optionByIdStr){
                        Map<String,Object> optionMap = new HashMap<>();
                        optionMap.put("optionName",questionOption.getContent());
                        if(answerOptionIdArray.contains(questionOption.getId()+"")){
                            if(questionAnswerOptionStr.length()>0){
                                questionAnswerOptionStr.append(",");
                            }
                            questionAnswerOptionStr.append(((char) (questionOption.getNumber() % 26 + (int) 'A'))+"");
                        }
                        optionMap.put("optionId",questionOption.getId());
                        optionMapList.add(optionMap);
                    }
                    questionMap.put("questionAnswerOptionStr",questionAnswerOptionStr);
                    questionMap.put("optionList",optionMapList);
                    questionMapList.add(questionMap);
                }
            }
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("questionList",questionMapList);
            return Jsonp_data.success(resultMap);
        }catch (Exception  e){
            e.printStackTrace();
            return Jsonp.error("服务异常");
        }
    }

    @RequestMapping(value = "questionHomeStatis",method = RequestMethod.GET)
    public Object questionHomeStatis(){
        try {
            Map<String,Object> resultMap = new HashMap<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            Date endTime = calendar.getTime();
            calendar.add(Calendar.MONTH,-1);
            Date startTime = calendar.getTime();
            Map<String,Object> params = new HashMap<>();
            params.put("endTime",endTime);
            params.put("startTime",startTime);
            params.put("isRight", ModuleConstant.ENUM_Y);
            Integer rightCount = questionRecordService.getTotalByMap(params);
            params.put("isRight", ModuleConstant.ENUM_N);
            Integer errorCount = questionRecordService.getTotalByMap(params);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("rightCount",rightCount);
            dataMap.put("errorCount",errorCount);
            resultMap.put("answerData",dataMap);
            //初始化时间
            calendar.setTime(endTime);
            List<Map<String,Object>> monthDataList = new ArrayList<>();
            Map<String,Object> monthParams = new HashMap<>();
            //查询半年的数据
            for(int i=0;i<6;i++){
                Date monthEndTime = calendar.getTime();
                calendar.set(Calendar.DAY_OF_MONTH,1);
                calendar.add(Calendar.MONTH,-1);
                Date monthStartTime = calendar.getTime();
                monthParams.put("endTime",monthEndTime);
                monthParams.put("startTime",monthStartTime);
                Integer monthTotal = questionRecordService.getTotalDisWorkerTab(monthParams);
                Map<String,Object> tempData = new HashMap<>();
                tempData.put("month",calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1));
                tempData.put("value",monthTotal);
                monthDataList.add(tempData);
            }
            resultMap.put("monthDataList",monthDataList);
            return Jsonp_data.success(resultMap);
        }catch (Exception  e){
            e.printStackTrace();
            return Jsonp.error("服务异常");
        }
    }

    public static void main(String[] args) {
        String optionIdStr = "256,257,258,259";
        List<String> answerOptionIdArray = Arrays.asList(optionIdStr.split(","));
        System.out.println(answerOptionIdArray.size()+":::"+answerOptionIdArray.contains(256+""));
    }
}
