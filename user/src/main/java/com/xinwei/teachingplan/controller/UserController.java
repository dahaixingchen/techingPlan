package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.UserBo;
import com.xinwei.teachingplan.service.UserService;
import com.xinwei.teachingplan.util.ApiMessage;
import com.xinwei.teachingplan.util.MessageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName: UserController
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


    @ApiOperation(value="登录")
    @PostMapping("/login")
    public ApiMessage<UserBo> login(@RequestBody UserBo userBo) {
        UserBo result = userService.doLogin(userBo);
        if (result == null) {
            return ApiMessage.error(MessageConstant.LOGIN_ERROR);
        } else {
            return ApiMessage.success(MessageConstant.LOGIN_SUCESS, result);
        }
    }

    @ApiOperation(value="忘记密码", notes = "基本请求")
    @PostMapping("/updatePassword")
    public ApiMessage<UserBo> updatePassword(@RequestBody UserBo user) {
        String message = userService.updatePassword(user);
        if (message != null && message.length() == 1 && Integer.parseInt(message) >= 1) {
            return ApiMessage.success(message);
        } else {
            return ApiMessage.error(message);
        }
    }

    @ApiOperation("创建账号")
    @PostMapping("/add-user")
    public ApiMessage<String> addUser(@RequestBody UserBo user){
        String  message = userService.addUser(user);
        if (message != null && message.length() == 1 && Integer.parseInt(message) >= 1){
            return ApiMessage.success(message);
        }else {
            return ApiMessage.error(message);
        }
    }


}
