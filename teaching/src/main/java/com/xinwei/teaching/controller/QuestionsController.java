package com.xinwei.teaching.controller;

import com.xinwei.teaching.bo.QuestionsBo;
import com.xinwei.teaching.entity.QuestionAnswer;
import com.xinwei.teaching.entity.QuestionBaseEntity;
import com.xinwei.teaching.service.QuestionsService;
import com.xinwei.teaching.util.ApiMessage;
import com.xinwei.teaching.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: QuestionsController
 * @Author chengfei
 * @Date 2020/6/28 16:39
 * @Version 1.0
 * @Description: 试卷模块
 **/
@Api("用户管理")
@RestController
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;

    @ApiOperation(value="添加试题")
    @PostMapping("/add-questions")
    public ApiMessage addQuestions(@RequestBody QuestionsBo questions){
        questionsService.addQuestions(questions);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS);
    }

    @ApiOperation(value="查看试题")
    @PostMapping("/query-questions")
    public ApiMessage<List<QuestionBaseEntity>> queryQuestions(@RequestBody QuestionsBo questions){
        List<QuestionBaseEntity> questionList = questionsService.queryQuestions(questions);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,questionList);
    }

    @ApiOperation(value="查看答案")
    @GetMapping("/query-answer")
    public ApiMessage<List<QuestionAnswer>> queryAnswer(Long questionsId){
        List<QuestionAnswer> answerList = questionsService.queryAnswer(questionsId);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,answerList);
    }

    @ApiOperation(value="添加到我")
    @GetMapping("/add-me")
    public ApiMessage addMe(Long questionsId){
        questionsService.addMe(questionsId);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS);
    }

}
