package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.*;
import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import com.xinwei.teachingplan.mapper.QuestionsMapper;
import com.xinwei.teachingplan.mapper.TeachMapper;
import com.xinwei.teachingplan.vo.TeachPointsVo;
import com.xinwei.teachingplan.vo.TeachPracticeVo;
import com.xinwei.teachingplan.vo.TeachVo;
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

    @Resource
    private QuestionsMapper questionsMapper;

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

    public void download(String teachId) {

    }

    public Integer delete(String teachId) {
        return teachMapper.delete(teachId);
    }

    public Integer addMe(PersonalBo personal) {
        return teachMapper.addMe(personal);
    }

    public TeachVo queryAllTeach(Long id) {
        TeachVo teach = teachMapper.queryTeachById(id);

        //包括完整试题的考点
        List<TeachPointsVo> points = teachMapper.queryPointById(teach.getId());
        for (TeachPointsVo point:points){
            String ids = point.getPointsQuestionIds();
            List<QuestionAnswerEntity> questions = this.getQuestions(ids);
            point.setPointsQuestionIdList(questions);
        }
        teach.setTeachPointsList(points);

        //包括完整试题的练习题
        List<TeachPracticeVo> practices = teachMapper.getPracticeById(teach.getId());
        for (TeachPracticeVo practice:practices){
            String ids = practice.getPracticeQuestionIds();
            List<QuestionAnswerEntity> questions = this.getQuestions(ids);
            practice.setPracticeQuestionIdList(questions);
        }
        teach.setTeachPracticeList(practices);

        //教案其他的试题
        List<QuestionAnswerEntity> questions = this.getQuestions(teach.getConsolidateQuestionIds());
        teach.setConsolidateQuestionList(questions);

        List<QuestionAnswerEntity> questions1 = this.getQuestions(teach.getSeniorQuestionIds());
        teach.setSeniorQuestionList(questions1);

        return teach;
    }

    private List<QuestionAnswerEntity> getQuestions(String ids) {
        //查询对应的多有试题
        List<QuestionAnswerEntity> questionAnswerEntities = new ArrayList<>();
        String[] split = ids.split(",");
        for (String questionsId:split){
            QuestionAnswerEntity questionAnswerEntity = questionsMapper.queryAnswer(Long.valueOf(questionsId));
            if (questionAnswerEntity != null){
                questionAnswerEntities.add(questionAnswerEntity);
            }
        }
        return questionAnswerEntities;
    }

}
