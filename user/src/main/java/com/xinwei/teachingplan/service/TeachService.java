package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.PersonalBo;
import com.xinwei.teachingplan.bo.QueryTeachBo;
import com.xinwei.teachingplan.bo.TeachBo;
import com.xinwei.teachingplan.mapper.TeachMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    public Integer addTeach(TeachBo teachBo) {
        Integer count = teachMapper.addTeach(teachBo);
        teachMapper.addTeachPoints(teachBo);
        teachMapper.addTeachPractice(teachBo);
        return count;
    }

    public List<TeachBo> queryTeach(QueryTeachBo teachBo) {
        return teachMapper.queryTeach(teachBo);

    }

    public Integer updateTeach(TeachBo teachBo) {

        return null;
    }

    public void download(String teachId) {

    }

    public Integer delete(String teachId) {
        return teachMapper.delete(teachId);
    }

    public Integer addMe(PersonalBo personal) {
        return teachMapper.addMe(personal);
    }
}
