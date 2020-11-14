package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.UserBo;
import com.xinwei.teachingplan.mapper.UserMapper;
import com.xinwei.teachingplan.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: UserService
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
        user.setName(user.getName().trim());
        if (user.getPhone() == null || "".equals(user.getPhone())) {
            return "请填写手机号";
        }
        user.setPhone(user.getPhone().trim());
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            return "请填写密码";
        }
        if (user.getConfirmPassword() == null || "".equals(user.getConfirmPassword())) {
            return "请填写确认密码";
        }
        user.setPassword(StringUtil.StringInMd5(user.getPassword()));
        //校验手机号是否重复
        String phoneUser = userMapper.queryPhone(user);
        if (phoneUser != null) {
            return "手机号码或用户名重复";
        }
        Integer count = userMapper.addUser(user);
        return count.toString();
    }

    public UserBo doLogin(UserBo user) {
        user.setName(user.getName().trim());
        user.setPhone(user.getPhone().trim());
        user.setPassword(StringUtil.StringInMd5(user.getPassword()));
        return userMapper.doLogin(user);

    }

    public String updatePassword(UserBo user) {
        String phoneUser = userMapper.queryPhone(user);
        if (phoneUser == null) {
            return "没有找到对应的账号";
        }
        user.setPassword(StringUtil.StringInMd5(user.getPassword()));
        Integer count = userMapper.updatePassword(user);
        return count.toString();
    }
}
