package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.UserBo;
import com.xinwei.teachingplan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Author chengfei
 * @Date 2020/6/17 20:31
 * @Version 1.0
 * @Description: TODO
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    /**
      * @Authon: chengfei
      * @Date: 2020/6/17 20:32
      * @Description: 创建账号
      **/
    @PostMapping("/add-user")
    public void addUser(@RequestBody UserBo user){
        userService.addUser(user);
    }
}
