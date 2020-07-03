package com.xinwei.teachingplan.mapper;

import com.xinwei.teachingplan.bo.UserBo;

public interface UserMapper {

    Integer addUser(UserBo user);

    UserBo doLogin(UserBo userBo);

    String queryPhone(String phone);

    Integer updatePassword(UserBo user);
}
