package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.UserBo;
import com.xinwei.teachingplan.mapper.UserMapper;
import com.xinwei.teachingplan.util.StringUtil;
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
            return "请填写名称";
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

    public UserBo doLogin(UserBo user) {
        user.setPassword(StringUtil.StringInMd5(user.getPassword()));
        return userMapper.doLogin(user);

    }

    public String updatePassword(UserBo user) {
        String phoneUser = userMapper.queryPhone(user.getPhone());
        if (phoneUser == null){
            return "没有找到对应的账号";
        }
        user.setPassword(StringUtil.StringInMd5(user.getPassword()));
        Integer count = userMapper.updatePassword(user);
        return count.toString();
    }
}
