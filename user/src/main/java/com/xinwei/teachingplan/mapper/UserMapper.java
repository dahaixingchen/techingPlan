package com.xinwei.teachingplan.mapper;

import com.xinwei.teachingplan.bo.UserBo;

import java.util.Map;

public interface UserMapper {

    void addUser(UserBo user);

    Map<String, Object> doLogin(UserBo userBo);

}
