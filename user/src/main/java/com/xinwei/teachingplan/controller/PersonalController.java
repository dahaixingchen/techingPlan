package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.QueryQuestionsBo;
import com.xinwei.teachingplan.bo.QueryTeachBo;
import com.xinwei.teachingplan.bo.TeachBo;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import com.xinwei.teachingplan.service.PersonalService;
import com.xinwei.teachingplan.util.ApiMessage;
import com.xinwei.teachingplan.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: PersonalController
 * @Date 2020/7/2 11:41
 * @Version 1.0
 * @Description: TODO
 **/
@Api("个人中心模块")
@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @Autowired
    private HttpServletRequest request;


    @ApiOperation(value="修改教案，这个在个人中心")
    @PostMapping("/update-teach")
    public ApiMessage updateTeach(@RequestBody TeachBo teachBo){
        String userId = request.getHeader("userId");
        teachBo.setUserId(userId);
        String message = personalService.updateTeach(teachBo);
        if (message != null){
            return ApiMessage.error(message);
        }
        return ApiMessage.success(MessageConstant.UPDATE_SUCCESS_MESSAGE);
    }

    @ApiOperation(value="我的试题，个人中心中默认展示我的教案")
    @PostMapping("/my-questions")
    public ApiMessage myQuestions(@RequestBody QueryQuestionsBo questions){
        String userId = request.getHeader("userId");
        questions.setUserId(Long.valueOf(userId));
        List<QuestionBaseEntity> questionList = personalService.myQuestions(questions);
        if (questionList != null){

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE,questionList);
        }else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value="我的教案，个人中心中默认展示我的教案")
    @PostMapping("/my-teach")
    public ApiMessage myTeach(@RequestBody QueryTeachBo queryTeachBo){
        String userId = request.getHeader("userId");
        queryTeachBo.setUserId(userId);
        List<TeachBo> Teachs = personalService.myTeach(queryTeachBo);
        if (Teachs != null){
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE,Teachs);
        }else{
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);

        }
    }


}
