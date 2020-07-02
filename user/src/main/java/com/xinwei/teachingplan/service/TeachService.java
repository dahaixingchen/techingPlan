package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.TeachBo;
import com.xinwei.teachingplan.mapper.TeachMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: TeachService
 * @Author chengfei
 * @Date 2020/6/29 14:37
 * @Version 1.0
 * @Description: TODO
 **/
@Service
public class TeachService {

    @Resource
    private TeachMapper teachMapper;

    public Integer addTeach(TeachBo teachBo) {
        return null;
    }

    public List<TeachBo> queryTeach(TeachBo teachBo) {
        return null;
    }

    public Integer updateTeach(TeachBo teachBo) {

        return null;
    }

    public void download(String teachId) {

    }

    public Integer delete(String teachId) {
        return null;
    }

    public Integer addMe(Long questionsId) {
        return null;
    }
}
