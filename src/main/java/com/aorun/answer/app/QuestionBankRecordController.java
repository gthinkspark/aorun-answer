package com.aorun.answer.app;

import com.aorun.answer.dto.UserDto;
import com.aorun.answer.model.QuestionBankRecord;
import com.aorun.answer.service.QuestionBankRecordService;
import com.aorun.answer.util.CheckObjectIsNull;
import com.aorun.common.annotation.ApiVersion;
import com.aorun.common.base.BasePagination;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionBankRecordController
 * @Description: TODO
 * @author: lg
 * @date: 2019/5/9 15:58
 */
@Controller
@ApiVersion(1)
@RequestMapping("app/{version}/questionBankRecord")
public class QuestionBankRecordController {
    @Autowired
    private QuestionBankRecordService questionBankRecordService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 答题记录接口
     * @param sid
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "questionBankRecordList",method = RequestMethod.GET)
    public Object questionBankRecordList( @RequestParam(name = "sid", required = true) String sid,
                                @RequestParam(name="pageIndex", required = true, defaultValue = "1") Integer pageIndex,
                                @RequestParam(name="pageSize", defaultValue = "20") Integer pageSize){
        try {
            UserDto user = null;
//            WorkerMember workerMember = null;
            if (!StringUtils.isEmpty(sid)) {
                user = (UserDto) redisUtil.get(sid);
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

            BasePagination<QuestionBankRecord> page = new BasePagination<>();
            Map<String,Object> params = new HashMap<>();
            params.put("workerId",user.getMemberId());
            page.setCurrentPage(pageIndex);
            page.setLimit(pageSize);
            page.setSortString("create_time desc");
            List<QuestionBankRecord> questionBankRecordList = questionBankRecordService.findByPage(page).getResult();
            List<Map<String,Object>> questionBankRecordMapList = new ArrayList<>();
            for(QuestionBankRecord questionBankRecord:questionBankRecordList){
                Map<String,Object> dataMap = new HashMap<>();
                dataMap.put("questionBankType",questionBankRecord.getQuestionBankType());
                dataMap.put("totalTime",questionBankRecord.getTotalTime());
                dataMap.put("accuracy",questionBankRecord.getAccuracy());
                dataMap.put("epoint",questionBankRecord.getEpoint());
                dataMap.put("month",questionBankRecord.getMonth());
                dataMap.put("star",questionBankRecord.getStar());
                dataMap.put("createTime",questionBankRecord.getCreateTime());
                questionBankRecordMapList.add(dataMap);
            }
            resultMap.put("questionBankRecordList",questionBankRecordMapList);
            return Jsonp_data.success(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return Jsonp.error("查询异常,请稍后重试");
        }

    }


}
