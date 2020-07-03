package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.UserBo;
import com.xinwei.teachingplan.mapper.UserMapper;
import com.xinwei.teachingplan.util.StringUtil;
import org.apache.catalina.User;
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


    public String addUser(UserBo user) {
        if (user.getName() == null || "".equals(user.getName())) {
            return Integer.toString(0);
        }
        user.setPassword(StringUtil.StringInMd5(user.getPassword()));
        //校验手机号是否重复
        String phoneUser = userMapper.queryPhone(user.getPhone());
        if (phoneUser != null){
            return "手机号码重复";
        }
        Integer count = userMapper.addUser(user);
        return count.toString();
    }

    public UserBo doLogin(UserBo userBo) {
        return userMapper.doLogin(userBo);
    }

    public Integer updatePassword(UserBo user) {
        return null;
    }
}
