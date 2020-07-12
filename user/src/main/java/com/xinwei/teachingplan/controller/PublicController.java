package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.entity.MenuEntity;
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
import java.util.Map;

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
    public PubilcFactory pubilcFactory;

    @ApiOperation(value = "试题左侧菜单")
    @GetMapping("/menuQueryQuestions")
    public ApiMessage<List<MenuEntity>> menuQueryQuestions() {
        PublicQuestionsImpl publicService = pubilcFactory.getImpl("questions");
        List<MenuEntity> menus =  publicService.menuQuery();
        if (menus != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, menus);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "教案左侧菜单")
    @GetMapping("/menuQueryTeach")
    public ApiMessage<List<MenuEntity>> menuQueryTeach() {
        PublicTeachImpl publicService = pubilcFactory.getImpl("teach");
        List<MenuEntity> menus =  publicService.menuQuery();
        if (menus != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, menus);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "试题新增，试题综合查询接口，查询所有的年份，标签，知识点")
    @GetMapping("/synQueryQuestions")
    public ApiMessage<Map<String, List<String>>> synQueryQuestions() {
        PublicQuestionsImpl publicService = pubilcFactory.getImpl("questions");
        Map<String, List<String>> syns =  publicService.synQueryQuestions();
        if (syns != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, syns);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "在个人中心页面，当点击我的试题的时候使用")
    @GetMapping("/synQueryPersonQuestions")
    public ApiMessage<Map<String, List<String>>> synQueryQuestions(Long userId) {
        PublicQuestionsImpl publicService = pubilcFactory.getImpl("questions");
        Map<String, List<String>> syns =  publicService.synQueryQuestions(userId);
        if (syns != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, syns);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "教案综合接口查询所有的课题，备课时间，在个人中心，试题查询模块中出现的地方")
    @ApiImplicitParam(name = "userId",value = "对应用户的id",required = true,paramType = "query")
    @GetMapping("/synQueryTeach")
    public ApiMessage<Map<String, List<String>>> synQueryTeach() {
        PublicTeachImpl publicService = pubilcFactory.getImpl("teach");
        Map<String, List<String>> syns =  publicService.synQueryTeach();
        if (syns != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, syns);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "在个人中心页面，刚进去默认就调用或是当点击我的教案调用")
    @ApiImplicitParam(name = "userId",value = "对应用户的id",required = true,paramType = "query")
    @GetMapping("/synQueryPersonTeach")
    public ApiMessage<Map<String, List<String>>> synQueryTeach(Long userId) {
        PublicTeachImpl publicService = pubilcFactory.getImpl("teach");
        Map<String, List<String>> syns =  publicService.synQueryTeach(userId);
        if (syns != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, syns);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }


}
