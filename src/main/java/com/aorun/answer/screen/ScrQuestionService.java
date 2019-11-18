package com.aorun.answer.screen;

import com.aorun.answer.model.Question;
import com.aorun.answer.model.QuestionBank;
import com.aorun.answer.model.QuestionOption;
import com.aorun.answer.service.QuestionBankService;
import com.aorun.answer.service.QuestionOptionService;
import com.aorun.answer.service.QuestionService;
import com.aorun.common.annotation.ApiVersion;
import com.aorun.common.base.BasePagination;
import com.aorun.common.util.ModuleConstant;
import com.aorun.common.util.jsonp.Jsonp;
import com.aorun.common.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 开始答题接口
     * @return
     */
    @ResponseBody
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
            }
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("questionList",questionMapList);
            return Jsonp_data.success(resultMap);
        }catch (Exception  e){
            e.printStackTrace();
            return Jsonp.error("服务异常");
        }
    }


}
