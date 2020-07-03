package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.service.PubilcFactory;
import com.xinwei.teachingplan.service.PublicQuestionsImpl;
import com.xinwei.teachingplan.service.PublicService;
import com.xinwei.teachingplan.service.PublicTeachImpl;
import com.xinwei.teachingplan.util.ApiMessage;
import com.xinwei.teachingplan.util.MessageConstant;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PublicController
 * @Author chengfei
 * @Date 2020/6/29 11:07
 * @Version 1.0
 * @Description: 公共的接口
 **/
@Api("公共接口")
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    PubilcFactory pubilcFactory;

    @ApiOperation(value = "查询所有的年级，在试卷，教案，个人中心三个模块中有年级出现的地方")
    @ApiImplicitParam(name = "dataType"
            , value = "查询分试卷和教案模块，如果为试题模块dataType传“questions”,反之传“teach”"
            , required = true
            ,paramType = "query")
    @GetMapping("/grade")
    public ApiMessage<List<String>> grade(String dataType) {
        PublicService publicService = pubilcFactory.getImpl(dataType);
        List<String> grades = publicService.grade();
        if (grades != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, grades);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }


    @ApiOperation(value = "查询所有的课程，在试卷，教案，个人中心三个模块中有课程出现的地方")
    @ApiImplicitParam(name = "dataType"
            , value = "查询分试卷和教案模块，如果为试题模块dataType传“questions”,反之传“teach”"
            , required = true
            ,paramType = "query")
    @GetMapping("/course")
    public ApiMessage<List<String>> course(String dataType) {
        PublicService publicService = pubilcFactory.getImpl(dataType);
        List<String> courses = publicService.course();
        if (courses != null) {

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, courses);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }


    @ApiOperation(value = "查询年份，在试卷，教案，个人中心三个模块中有年份出现的地方")
    @ApiImplicitParam(name = "dataType"
            , value = "查询分试卷和教案模块，如果为试题模块dataType传“questions”,反之传“teach”"
            , required = true
            ,paramType = "query")
    @GetMapping("/year")
    public ApiMessage<List<String>> year(String dataType) {
        PublicService publicService = pubilcFactory.getImpl(dataType);
        List<String> years = publicService.year();
        if (years == null){
            return ApiMessage.success(MessageConstant.LOGIN_SUCESS, years);
        }else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }


    @ApiOperation(value = "查询所有标签，在试卷，教案，个人中心三个模块中有标签出现的地方")
    @ApiImplicitParam(name = "dataType"
            , value = "查询分试卷和教案模块，如果为试题模块dataType传“questions”,反之传“teach”"
            , required = true
            ,paramType = "query")
    @GetMapping("/label")
    public ApiMessage<List<String>> label(String dataType) {
        PublicQuestionsImpl publicService = pubilcFactory.getImpl("questions");
        List<String> labels = publicService.label();
        if (labels != null) {

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, labels);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }


    @ApiOperation(value = "查询所有知识点，在试卷，教案，个人中心三个模块中有知识点出现的地方")
    @ApiImplicitParam(name = "dataType"
            , value = "查询分试卷和教案模块，如果为试题模块dataType传“questions”,反之传“teach”"
            , required = true
            ,paramType = "query")
    @GetMapping("/knowledges")
    public ApiMessage<List<String>> knowledge(String dataType) {
        PublicQuestionsImpl publicService = pubilcFactory.getImpl("questions");
        List<String> knowledges = publicService.knowledge();
        if (knowledges != null) {

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, knowledges);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "查询所有知识点，在试卷，教案，个人中心三个模块中有知识点出现的地方")
    @ApiImplicitParam(name = "dataType"
            , value = "查询分试卷和教案模块，如果为试题模块dataType传“questions”,反之传“teach”"
            , required = true
            ,paramType = "query")
    @GetMapping("/teachTopic")
    public ApiMessage<List<String>> teachTopic() {
        PublicTeachImpl TeachImpl = pubilcFactory.getImpl("teach");
        List<String> teachTopics = TeachImpl.teachTopic();
        if (teachTopics != null) {

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, teachTopics);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }


}
