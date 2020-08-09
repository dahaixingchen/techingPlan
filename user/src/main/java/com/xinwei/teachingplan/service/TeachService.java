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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String addTeach(TeachBo teachBo) {
        //数据校验
        String message = this.checkData(teachBo);
        if (null != message){
            return message;
        }
        Integer count = teachMapper.addTeach(teachBo);
        teachBo.getTeachPointsList().forEach(point->{
            point.setTeachId(teachBo.getTeachId());
        });
        if (teachBo.getTeachPointsList().size() > 0){
            teachMapper.addTeachPoints(teachBo.getTeachPointsList());
        }

        teachBo.getTeachPracticeList().forEach(practice->{
            practice.setTeachId(teachBo.getTeachId());
        });
        if (teachBo.getTeachPracticeList().size() > 0){
            teachMapper.addTeachPractice(teachBo.getTeachPracticeList());

        }
        //添加个人中心创建人
        teachMapper.addMe(new PersonalBo(teachBo.getUserId(),teachBo.getTeachId(),""));
        return message;
    }
    /**
      * @Description: 校验数据
      **/
    public String checkData(TeachBo teachBo){
        //数据校验
        if(null == teachBo.getTeachName() || "".equals(teachBo.getTeachName())){
            return "教师名称不能为空";
        }
        if(null == teachBo.getGrade() || "".equals(teachBo.getGrade())){
            return "年级不能为空";
        }
        if(null == teachBo.getLessonsTime() || "".equals(teachBo.getLessonsTime())){
            String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
            Pattern p = Pattern.compile(eL);
            Matcher m = p.matcher(teachBo.getLessonsTime());
            boolean dateFlag = true;
            dateFlag = m.matches();
            if (!dateFlag) {
                return "上课时间格式填写不对，正确例子：2020-01-01";
            }
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
            try
            {
                Date date = format.parse(teachBo.getLessonsTime());
            } catch (ParseException e)
            {
                dateFlag=false;
            }
            if (!dateFlag) {
                return "上课时间格式填写不对，正确例子：2020-01-01";
            }
            return "上课时间不能为空";
        }
        if(null == teachBo.getPrepareLessonsTime() || "".equals(teachBo.getPrepareLessonsTime())){
            String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
            Pattern p = Pattern.compile(eL);
            Matcher m = p.matcher(teachBo.getPrepareLessonsTime());
            boolean dateFlag = true;
            dateFlag = m.matches();
            if (!dateFlag) {
                return "备课时间格式填写不对，正确例子：2020-01-01";
            }
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-mm");
            try
            {
                Date date = format.parse(teachBo.getPrepareLessonsTime());
            } catch (ParseException e)
            {
                dateFlag=false;
            }
            if (!dateFlag) {
                return "备课时间格式填写不对，正确例子：2020-01-01";
            }
            return "备课时间不能为空";
        }
        if(null == teachBo.getStudentName() || "".equals(teachBo.getStudentName())){
            return "学生名称不能为空";
        }
        if(null == teachBo.getCourse() || "".equals(teachBo.getCourse())){
            return "科目不能为空";
        }
        if(null == teachBo.getTeachTopic() || "".equals(teachBo.getTeachTopic())){
            return "课题不能为空";
        }
        if(null == teachBo.getTeachType() || "".equals(teachBo.getTeachType())){
            return "课型不能为空";
        }
        if(null == teachBo.getTeachSource() || "".equals(teachBo.getTeachSource())){
            return "课题出处不能为空";
        }
        if(null == teachBo.getTeachGoal() || "".equals(teachBo.getTeachGoal())){
            return "教学目标不能为空";
        }
        if(null == teachBo.getTeachKeyDifficulty() || "".equals(teachBo.getTeachKeyDifficulty())){
            return "教学重点难点不能为空";
        }
        if(null == teachBo.getReview() || "".equals(teachBo.getReview())){
            return "温故知新不能为空";
        }
        if(null == teachBo.getKeyPoint() || "".equals(teachBo.getKeyPoint())){
            return "考点突破不能为空";
        }
        if(null == teachBo.getSeniorQuestionIds() || "".equals(teachBo.getSeniorQuestionIds())){
            return "中考演练试题不能为空";
        }
        if(null == teachBo.getConclude() || "".equals(teachBo.getConclude())){
            return "总结归纳不能为空";
        }
//        if(null == teachBo.getConsolidate() || "".equals(teachBo.getConsolidate())){
//            return "自我巩固不能为空";
//        }
        if(null == teachBo.getConsolidateQuestionIds() || "".equals(teachBo.getConsolidateQuestionIds())){
            return "自我巩固练习题不能为空";
        }
        if(null == teachBo.getTeachPracticeList() || "".equals(teachBo.getTeachPracticeList().size()<=0)){
            return "每周一练不能为空";
        }else {
            for (int i = 0; i < teachBo.getTeachPracticeList().size(); i++) {
                if(null == teachBo.getTeachPracticeList().get(i).getPracticeQuestionIds()
                        || "".equals(teachBo.getTeachPracticeList().get(i).getPracticeQuestionIds())){
                    return new StringBuilder("每周一练,周").append(i+1).append("对应的练习题不能为空").toString();
                }
                if(null == teachBo.getTeachPracticeList().get(i).getSignature()
                        || "".equals(teachBo.getTeachPracticeList().get(i).getSignature())){
                    return new StringBuilder("每周一练,周").append(i+1).append("对应的签名不能为空").toString();
                }
            }
        }
        if(null == teachBo.getTeachPointsList() || "".equals(teachBo.getTeachPointsList().size()<=0)){
            return "考点不能为空";
        }else {
            for (int i = 0; i < teachBo.getTeachPointsList().size(); i++) {
                if(null == teachBo.getTeachPointsList().get(i).getPoints()
                        || "".equals(teachBo.getTeachPointsList().get(i).getPoints())){
                    return new StringBuilder("考点").append(i+1).append("对应的考点梳理不能为空").toString();
                }
                if(null == teachBo.getTeachPointsList().get(i).getAnalyse()
                        || "".equals(teachBo.getTeachPointsList().get(i).getAnalyse())){
                    return new StringBuilder("考点").append(i+1).append("对应的剖析不能为空").toString();
                }
                if(null == teachBo.getTeachPointsList().get(i).getPointsQuestionIds()
                        || "".equals(teachBo.getTeachPointsList().get(i).getPointsQuestionIds())){
                    return new StringBuilder("考点").append(i+1).append("对应的小试牛刀不能为空").toString();
                }
            }
        }
        return null;
    }

    /**
      * @Description: 查看教案
      **/
    public List<TeachBo> queryTeach(QueryTeachBo teachBo) {
        teachBo.setFlag(2);
        List<TeachBo> teachs = teachMapper.queryTeach(teachBo);
        for(TeachBo teach :teachs){
            List<TeachPointsBo> points = teachMapper.queryPoint(Long.valueOf(teach.getId()));
            teach.setTeachPointsList(points);
            List<TeachPracticeBo> practices = teachMapper.getPractice(Long.valueOf(teach.getId()));
            teach.setTeachPracticeList(practices);
        }
        return teachs;

    }

    public void download(String teachId) {

    }

    public Integer delete(String teachId, String userId,String userType) {
        Integer count = 0;
        if (Long.valueOf(userType) == 1){
            count =teachMapper.delete(teachId);
        }else{
            count =teachMapper.deletePersonal(teachId,userId);
        }
        return count;
    }

    public Integer addMe(PersonalBo personal) {
        personal.setFlag(1);
        return teachMapper.addMe(personal);
    }

    public TeachVo queryAllTeach(Long id) {
        TeachVo teach = teachMapper.queryTeachById(id);

        //包括完整试题的考点
        if (teach != null){
            List<TeachPointsVo> points = teachMapper.queryPointById(teach.getId());
            for (TeachPointsVo point:points){
                String ids = point.getPointsQuestionIds();
                Map<String, Map<String, Object>> questions = this.getQuestions(ids);
                point.setPointsQuestionIds_detail(questions);
            }
            teach.setTeachPointsList(points);

            //包括完整试题的练习题
            List<TeachPracticeVo> practices = teachMapper.getPracticeById(teach.getId());
            for (TeachPracticeVo practice:practices){
                String ids = practice.getPracticeQuestionIds();
                Map<String, Map<String, Object>> questions = this.getQuestions(ids);
                practice.setPracticeQuestionIds_detail(questions);
            }
            teach.setTeachPracticeList(practices);

            //教案其他的试题
            Map<String, Map<String, Object>> questions = this.getQuestions(teach.getConsolidateQuestionIds());
            teach.setConsolidateQuestionIds_detail(questions);

            Map<String, Map<String, Object>> questions1 = this.getQuestions(teach.getSeniorQuestionIds());
            teach.setSeniorQuestionIds_detail(questions1);
        }
        return teach;
    }

    public TeachVo queryAllTeachDownload(Long id) {
        TeachVo teach = teachMapper.queryTeachById(id);

        //包括完整试题的考点
        if (teach != null){
            List<TeachPointsVo> points = teachMapper.queryPointById(teach.getId());
            for (TeachPointsVo point:points){
                String ids = point.getPointsQuestionIds();
                List<QuestionAnswerEntity> questions = this.getQuestionsDownload(ids);
                point.setPointsQuestionIdList(questions);
            }
            teach.setTeachPointsList(points);

            //包括完整试题的练习题
            List<TeachPracticeVo> practices = teachMapper.getPracticeById(teach.getId());
            for (TeachPracticeVo practice:practices){
                String ids = practice.getPracticeQuestionIds();
                List<QuestionAnswerEntity> questions = this.getQuestionsDownload(ids);
                practice.setPracticeQuestionIdList(questions);
            }
            teach.setTeachPracticeList(practices);

            //教案其他的试题
            List<QuestionAnswerEntity> questions = this.getQuestionsDownload(teach.getConsolidateQuestionIds());
            teach.setConsolidateQuestionList(questions);

            List<QuestionAnswerEntity> questions1 = this.getQuestionsDownload(teach.getSeniorQuestionIds());
            teach.setSeniorQuestionList(questions1);
        }
        return teach;
    }

    private Map<String, Map<String, Object>> getQuestions(String ids) {
        //查询对应的多有试题
        Map<String, Map<String, Object>> questionAnswerEntities = new HashMap<>();
//        Map<String,Object> questionAnswerEntities = new HashMap<>();
        String[] split = ids.split(",");
        for (String questionsId:split){
            if (questionsId != null && !"".equals(questionsId)){
                Map<String,Object> questionAnswerEntity = questionsMapper.queryAnswerMap(Long.valueOf(questionsId));
                if (questionAnswerEntity != null){
                    questionAnswerEntities.put(questionsId,questionAnswerEntity);
                }
            }
        }
        return questionAnswerEntities;
    }

    private List<QuestionAnswerEntity> getQuestionsDownload(String ids) {
        //查询对应的多有试题
        List<QuestionAnswerEntity> questionAnswerEntities = new ArrayList<>();
        String[] split = ids.split(",");
        for (String questionsId:split){
            if (questionsId != null && !"".equals(questionsId)){
                QuestionAnswerEntity questionAnswerEntity = questionsMapper.queryAnswer(Long.valueOf(questionsId));
                if (questionAnswerEntity != null){
                    questionAnswerEntities.add(questionAnswerEntity);
                }
            }
        }
        return questionAnswerEntities;
    }

}
