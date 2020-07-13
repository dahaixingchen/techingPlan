package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.*;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import com.xinwei.teachingplan.mapper.PersonalMapper;
import com.xinwei.teachingplan.mapper.QuestionsMapper;
import com.xinwei.teachingplan.mapper.TeachMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private TeachMapper teachMapper;

    @Resource
    private QuestionsMapper questionsMapper;

    @Transactional(rollbackFor = Exception.class)
    public Integer updateTeach(TeachBo teachBo) {
        Integer count = personalMapper.updateTeach(teachBo);
        personalMapper.deletePoints(teachBo.getTeachId());
        personalMapper.insertPoint(teachBo.getTeachPointsList());
        personalMapper.deletePractice(teachBo.getTeachId());
        personalMapper.updatePractice(teachBo.getTeachPracticeList());
        return count;
    }

    public List<TeachBo> myTeach(QueryTeachBo teachBo) {
        teachBo.setFlag(1);
        List<TeachBo> teachs = teachMapper.queryTeach(teachBo);
        for(TeachBo teach :teachs){
            List<TeachPointsBo> points = teachMapper.queryPoint(teach.getId());
            teach.setTeachPointsList(points);
            List<TeachPracticeBo> practices = teachMapper.getPractice(teach.getId());
            teach.setTeachPracticeList(practices);
        }
        return teachs;
    }

    public List<QuestionBaseEntity> myQuestions(QueryQuestionsBo questions) {
        questions.setFlag(1);
        return questionsMapper.queryQuestions(questions);
    }
}
