package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.QueryTeachBo;
import com.xinwei.teachingplan.bo.TeachBo;
import com.xinwei.teachingplan.service.TeachService;
import com.xinwei.teachingplan.util.ApiMessage;
import com.xinwei.teachingplan.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value="新增教案")
    @PostMapping("/add-teach")
    public ApiMessage<List<String>> addTeach(@RequestBody TeachBo teachBo){
        Integer count = teachService.addTeach(teachBo);
        if (count != null && count >= 1){
            return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE,"共新增数据 "+count+" 条");
        }else{

            return ApiMessage.success(MessageConstant.ADD_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value="查询教案，在教案页面（个人中心的教案页面）没点一个关键字(“期中”，“填空”，“中考试卷”"+
            " ，“约分”，“知识点”)都会触发这个接口")
    @PostMapping("/query-teach")
    public ApiMessage<List<String>> queryTeach(@RequestBody QueryTeachBo queryTeachBo){
        List<TeachBo> Teachs = teachService.queryTeach(queryTeachBo);
        if (Teachs != null){
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE,Teachs);
        }else{
            return ApiMessage.success(MessageConstant.QUERY_ERROR_MESSAGE);

        }
    }

    @ApiOperation(value="下载教案(在个人中心和教案模块都有此接口),成Word文档的形式")
    @GetMapping("/download")
    public ApiMessage<List<String>> download(String teachId){
        teachService.download(teachId);
        return ApiMessage.success(MessageConstant.LOGIN_SUCESS);
    }

    @ApiOperation(value="删除教案")
    @GetMapping("/delete")
    public ApiMessage<Integer> delete(String teachId){
        Integer count = teachService.delete(teachId);
        if (count != null && count >= 1){
            return ApiMessage.success(MessageConstant.DELETE_SUCCESS_MESSAGE,"共删除数据 "+count+" 条");
        }else{

            return ApiMessage.success(MessageConstant.DELETE_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value="添加到我")
    @ApiImplicitParam(name = "questionsId",value = "对应教案的id",required = true,paramType = "query")
    @GetMapping("/add-me")
    public ApiMessage addMe(Long questionsId){
        Integer count = teachService.addMe(questionsId);
        if (count == 1){
            return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE);
        }else {
            return ApiMessage.error(MessageConstant.ADD_ERROR_MESSAGE);
        }
    }
}
