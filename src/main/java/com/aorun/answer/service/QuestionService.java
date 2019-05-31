package com.aorun.answer.service;

import com.aorun.answer.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionService {

    List<Question> selectByBankId(Long questionBankId);

}
