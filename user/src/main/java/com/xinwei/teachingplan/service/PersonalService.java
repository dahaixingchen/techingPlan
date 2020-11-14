package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.*;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import com.xinwei.teachingplan.mapper.PersonalMapper;
import com.xinwei.teachingplan.mapper.QuestionsMapper;
import com.xinwei.teachingplan.mapper.TeachMapper;
import com.xinwei.teachingplan.util.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PersonalService
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

    @Autowired
    private TeachService teachService;

    @Transactional(rollbackFor = Exception.class)
    public String updateTeach(TeachBo teachBo) {
        String message = teachService.addTeach(teachBo);

        if (message == null) {
            personalMapper.deletePerson(teachBo);
        }
        return message;
//        //数据校验
//        String message = teachService.checkData(teachBo);
//        if (null != message){
//            return message;
//        }
//        teachMapper.addTeach(teachBo);
////        Integer count = personalMapper.updateTeach(teachBo);
////        personalMapper.deletePoints(teachBo.getId());
////        personalMapper.deletePractice(teachBo.getId());
////        teachBo.getTeachPointsList().forEach(point->{
////            point.setTeachId(teachBo.getId());
////        });
////        if (teachBo.getTeachPointsList().size() > 0){
////            teachMapper.addTeachPoints(teachBo.getTeachPointsList());
////        }
////
////        teachBo.getTeachPracticeList().forEach(practice->{
////            practice.setTeachId(teachBo.getId());
////        });
////        if (teachBo.getTeachPracticeList().size() > 0){
////            teachMapper.addTeachPractice(teachBo.getTeachPracticeList());
////
////        }
//        //添加个人中心创建人
//        teachMapper.addMe(new PersonalBo(teachBo.getUserId(),teachBo.getId(),""));
//        return message;
    }


    public List<TeachBo> myTeach(QueryTeachBo teachBo) {
        teachBo.setFlag(1);
        List<TeachBo> teachs = teachMapper.queryTeach(teachBo);
        for (TeachBo teach : teachs) {
            List<TeachPointsBo> points = teachMapper.queryPoint(Long.valueOf(teach.getId()));
            teach.setTeachPointsList(points);
            List<TeachPracticeBo> practices = teachMapper.getPractice(Long.valueOf(teach.getId()));
            teach.setTeachPracticeList(practices);
        }
        return teachs;
    }

    public List<QuestionBaseEntity> myQuestions(QueryQuestionsBo questions) {
        questions.setFlag(1);
        return questionsMapper.queryQuestions(questions);
    }
}
