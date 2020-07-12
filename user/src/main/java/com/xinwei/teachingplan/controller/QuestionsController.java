package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.PersonalBo;
import com.xinwei.teachingplan.bo.QueryQuestionsBo;
import com.xinwei.teachingplan.bo.QuestionsBo;
import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import com.xinwei.teachingplan.service.QuestionsService;
import com.xinwei.teachingplan.util.ApiMessage;
import com.xinwei.teachingplan.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@Api("试卷模块")
@RestController
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;

    @ApiOperation(value="新增试题")
    @PostMapping("/add-questions")
    public ApiMessage addQuestions(@RequestBody QuestionsBo questions){
        Integer count = questionsService.addQuestions(questions);
        if (count != null &&count == 1){
            return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE);
        }else {
            return ApiMessage.error(MessageConstant.ADD_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value="删除试题")
    @GetMapping("/delete")
    public ApiMessage delete(Long id){
        Integer count = questionsService.delete(id);
        if (count == 1){
            return ApiMessage.success(MessageConstant.DELETE_SUCCESS_MESSAGE);
        }else {
            return ApiMessage.error(MessageConstant.DELETE_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value="查看试题，在试题页面每点一个关键字(“期中”，“填空”，“中考试卷”" +
            "“知识点”)都会触发这个接口")
    @PostMapping("/query-questions")
    public ApiMessage<List<QuestionBaseEntity>> queryQuestions(@RequestBody QueryQuestionsBo  questions){
        List<QuestionBaseEntity> questionList = questionsService.queryQuestions(questions);
        if (questionList != null){

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE,questionList);
        }else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value="查看答案")
    @ApiImplicitParam(name = "questionsId",value = "对应试题的id",required = true,paramType = "query")
    @GetMapping("/query-answer")
    public ApiMessage<List<QuestionAnswerEntity>> queryAnswer(Long questionsId){
        List<QuestionAnswerEntity> answerList = questionsService.queryAnswer(questionsId);
        if(answerList != null){

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE,answerList);
        }else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value="添加到我")
    @PostMapping("/add-me")
    public ApiMessage addMe(@RequestBody PersonalBo personal){
        Integer count = questionsService.addMe(personal);
        if (count == 1){
            return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE);
        }else {
            return ApiMessage.error(MessageConstant.ADD_ERROR_MESSAGE);
        }
    }

}
