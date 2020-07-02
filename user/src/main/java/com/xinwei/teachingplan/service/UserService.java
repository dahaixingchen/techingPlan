package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.UserBo;
import com.xinwei.teachingplan.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: UserService
 * @Author chengfei
 * @Date 2020/6/17 21:12
 * @Version 1.0
 * @Description: TODO
 **/
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public Integer addUser(UserBo user) {
        Integer count = userMapper.addUser(user);
        return count;
    }

    public UserBo doLogin(UserBo userBo) {
        return userMapper.doLogin(userBo);
    }

    public Integer updatePassword(UserBo user) {
        return null;
    }
}
