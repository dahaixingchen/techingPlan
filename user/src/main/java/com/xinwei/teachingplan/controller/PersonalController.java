package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.TeachBo;
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

/**
 * @ClassName: PersonalController
 * @Author chengfei
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


    @ApiOperation(value="修改教案，这个在个人中心")
    @PostMapping("/update-teach")
    public ApiMessage updateTeach(@RequestBody TeachBo teachBo){
        Integer count = personalService.updateTeach(teachBo);
        if (count != null && count >= 1){
            return ApiMessage.success(MessageConstant.UPDATE_SUCCESS_MESSAGE,"共修改的数据 "+count+" 条");
        }else{

            return ApiMessage.success(MessageConstant.UPDATE_ERROR_MESSAGE);
        }
    }


}