package com.xinwei.teaching.controller;

import com.xinwei.teaching.bo.TeachBo;
import com.xinwei.teaching.service.TeachService;
import com.xinwei.teaching.util.ApiMessage;
import com.xinwei.teaching.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ApiOperation(value="添加教案")
    @PostMapping("/add-teach")
    public ApiMessage<List<String>> addTeach(@RequestBody TeachBo teachBo){
        teachService.addTeach(teachBo);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS);
    }

    @ApiOperation(value="修改教案")
    @PostMapping("/update-teach")
    public ApiMessage updateTeach(@RequestBody TeachBo teachBo){
        teachService.updateTeach(teachBo);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS);
    }

    @ApiOperation(value="查询教案")
    @PostMapping("/query-teach")
    public ApiMessage<List<String>> queryTeach(@RequestBody TeachBo teachBo){
        List<String> Teachs = teachService.queryTeach(teachBo);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS,Teachs);
    }


}
