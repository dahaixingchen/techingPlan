package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.*;
import com.xinwei.teachingplan.mapper.TeachMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        teachBo.getTeachPointsList().forEach(point->{
            point.setTeachId(teachBo.getTeachId());
        });
        teachMapper.addTeachPoints(teachBo.getTeachPointsList());

        teachBo.getTeachPracticeList().forEach(practice->{
            practice.setTeachId(teachBo.getTeachId());
        });
        teachMapper.addTeachPractice(teachBo.getTeachPracticeList());
        return count;
    }

    public List<TeachBo> queryTeach(QueryTeachBo teachBo) {
        teachBo.setFlag(0);
        List<TeachBo> teachs = teachMapper.queryTeach(teachBo);
        for(TeachBo teach :teachs){
            List<TeachPointsBo> points = teachMapper.queryPoint(teach.getId());
            teach.setTeachPointsList(points);
            List<TeachPracticeBo> practices = teachMapper.getPractice(teach.getId());
            teach.setTeachPracticeList(practices);
        }
        return teachs;

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
