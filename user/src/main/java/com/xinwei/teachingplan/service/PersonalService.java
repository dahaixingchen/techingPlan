package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.TeachBo;
import com.xinwei.teachingplan.mapper.PersonalMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PersonalService
 * @Author chengfei
 * @Date 2020/7/2 11:51
 * @Version 1.0
 * @Description: TODO
 **/
@Service
public class PersonalService {
    @Resource
    private PersonalMapper personalMapper;

    public Integer updateTeach(TeachBo teachBo) {
        return null;
    }
}
