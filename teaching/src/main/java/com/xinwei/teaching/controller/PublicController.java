package com.xinwei.teaching.controller;

import com.xinwei.teaching.service.PublicService;
import com.xinwei.teaching.util.ApiMessage;
import com.xinwei.teaching.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private PublicService publicService;
    
    @ApiOperation(value="查询所有的年级")
    @GetMapping("/grade")
    public ApiMessage<List<String>> grade(String grade){
        List<String> grades = publicService.grade(grade);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,grades);
    }

    @ApiOperation(value="查询所有的课程")
    @GetMapping("/course")
    public ApiMessage<List<String>> course(String course){
        List<String> courses = publicService.course(course);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,courses);
    }

    @ApiOperation(value="查询年份")
    @GetMapping("/year")
    public ApiMessage<List<String>> year(String year){
        List<String> years = publicService.year(year);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,years);
    }

    @ApiOperation(value="查询所有标签")
    @GetMapping("/label")
    public ApiMessage<List<String>> label(String label){
        List<String> labels = publicService.course(label);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,labels);
    }


    @ApiOperation(value="查询所有知识点")
    @GetMapping("/label")
    public ApiMessage<List<String>> knowledge(String knowledge){
        List<String> knowledges = publicService.course(knowledge);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,knowledges);
    }
}
