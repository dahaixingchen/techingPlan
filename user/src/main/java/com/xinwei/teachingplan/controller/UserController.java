package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.UserBo;
import com.xinwei.teachingplan.service.UserService;
import com.xinwei.teachingplan.util.ApiMessage;
import com.xinwei.teachingplan.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName: UserController
 * @Author chengfei
 * @Date 2020/6/17 20:31
 * @Version 1.0
 * @Description: 人员管理系统
 **/
@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
      * @Authon: chengfei
      * @Date: 2020/6/22 20:04
      * @Description: 登录
      **/
    @ApiOperation(value="登录")
    @PostMapping("/login")
    public ApiMessage login(@RequestBody UserBo userBo) {
        Map<String, Object> result = userService.doLogin(userBo);
        if ("error".equals(result.get("status").toString())) {
            return ApiMessage.error(result.get("data").toString());
        } else {
            return ApiMessage.success(MessageConstant.LOGIN_SUCESS, result.get("data"));
        }
    }

    /**
     * 修改密码
     * @param
     * @return
     */
    @ApiOperation(value="忘记密码", notes = "基本请求")
    @PostMapping("/updatePassword")
    public ApiMessage updatePassword(@RequestBody UserBo user) {
        Map<String, Object> result = userService.updatePassword(user);
        if ("error".equals(result.get("status").toString())) {
            return ApiMessage.error(result.get("data").toString());
        } else {
            return ApiMessage.success(MessageConstant.UPDATE_SUCCESS_MESSAGE);
        }
    }

    /**
      * @Authon: chengfei
      * @Date: 2020/6/17 20:32
      * @Description: 创建账号
      **/
    @ApiOperation("创建账号")
    @PostMapping("/add-user")
    public void addUser(@RequestBody UserBo user){
        userService.addUser(user);
    }


}
