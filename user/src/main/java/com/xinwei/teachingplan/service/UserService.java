package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.UserBo;
import com.xinwei.teachingplan.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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


    public void addUser(UserBo user) {
        userMapper.addUser(user);
    }

    public Map<String, Object> doLogin(UserBo userBo) {
        return userMapper.doLogin(userBo);
    }

    public Map<String, Object> updatePassword(UserBo user) {
        return null;
    }
}
