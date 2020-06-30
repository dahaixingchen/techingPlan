package com.xinwei.teaching.controller;

import com.xinwei.teaching.service.TeachService;
import com.xinwei.teaching.util.ApiMessage;
import com.xinwei.teaching.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TeachController
 * @Author chengfei
 * @Date 2020/6/29 12:49
 * @Version 1.0
 * @Description: TODO
 **/
@Api("教案模块")
@RestController
@RequestMapping("/teach")
public class TeachController {

    @Autowired
    private TeachService teachService;

    @ApiOperation(value="查询所有的年级")
    @PostMapping("/add-teach")
    public ApiMessage<List<String>> addTeach(){
//        List<String> grades = teachService.addTeach();
//        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,grades);
        return null;
    }
}
