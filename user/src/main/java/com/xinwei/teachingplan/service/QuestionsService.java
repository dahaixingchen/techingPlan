package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.MenuBo;
import com.xinwei.teachingplan.bo.PersonalBo;
import com.xinwei.teachingplan.bo.QueryQuestionsBo;
import com.xinwei.teachingplan.bo.QuestionsBo;
import com.xinwei.teachingplan.entity.MenuEntity;
import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import com.xinwei.teachingplan.mapper.QuestionsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: QuestionsService
 * @Date 2020/6/28 16:43
 * @Version 1.0
 * @Description: TODO
 **/
@Service
public class QuestionsService {
    @Resource
    private QuestionsMapper questionsMapper;

    @Resource
    private PubilcFactory pubilcFactory;

    /**
     * @return
     * @Authon: chengfei
     * @Date: 2020/6/28 18:21
     * @Description: 添加试题
     */
    @Transactional(rollbackFor = Exception.class)
    public String addQuestions(QuestionsBo questions) {
        //数据校验
        if (questions==null){
            return "试题不能为空";
        }
        if (questions.getGrade() == null || "".equals(questions.getGrade())){
            return "年级不能为空";
        }
        if (questions.getCourse() == null || "".equals(questions.getCourse())){
            return "课程不能为空";
        }
        if (questions.getQuestionsType() == null || "".equals(questions.getQuestionsType())){
            return "试题类型不能为空";
        }
        if (questions.getComplexity() == null || "".equals(questions.getComplexity())){
            return "试题难易程度不能为空";
        }
        if (questions.getYear() == null || "".equals(questions.getYear())){
            return "试题年份不能为空";
        }
        if (questions.getLabel() == null || "".equals(questions.getLabel())){
            return "试题标签不能为空";
        }
        if (questions.getKnowledge() == null || "".equals(questions.getKnowledge())){
            return "试题所属知识点不能为空";
        }
        if (questions.getQuestionsStart() == null || "".equals(questions.getQuestionsStart())){
            return "试题题干不能为空";
        }
        if (questions.getQuestionsAnswer() == null || "".equals(questions.getQuestionsAnswer())){
            return "试题答案不能为空";
        }
        if (questions.getQuestionsAnalyze() == null || "".equals(questions.getQuestionsAnalyze())){
            return "试题分析不能为空";
        }
        if (questions.getQuestionsRemark() == null || "".equals(questions.getQuestionsRemark())){
            return "试题点评不能为空";
        }
        if (questions.getQuestionsExplain() == null || "".equals(questions.getQuestionsExplain())){
            return "试题解答不能为空";
        }

        //添加试题左侧栏，年级，科目，知识点属性
        PublicQuestionsImpl questions1 = pubilcFactory.getImpl("questions");
        List<MenuEntity> menuEntities = questions1.menuQuery();
        HashMap<String, MenuEntity> oneMap = new HashMap<>();
        for (MenuEntity menuEntity:menuEntities){
            oneMap.put(menuEntity.getName(),menuEntity);
        }
        MenuBo menuBo = new MenuBo(questions.getGrade());
        //第一层年级的判断
        if (!oneMap.containsKey(questions.getGrade())){
            //如果不存在，直接新增
            menuBo.setFatherId(-1L);

            //新增三层的目录
            questionsMapper.insertMenu(menuBo);
            menuBo.setFatherId(menuBo.getOneId());
            menuBo.setName(questions.getCourse());
            questionsMapper.insertMenu(menuBo);
            menuBo.setFatherId(menuBo.getOneId());
            menuBo.setName(questions.getKnowledge());
            questionsMapper.insertMenu(menuBo);
        }else{
            //如果存在，继续往下面遍历
            MenuEntity menuEntity = oneMap.get(questions.getGrade());
            HashMap<String, MenuEntity> twoMap = new HashMap<>();
            for(MenuEntity menu:menuEntity.getChildNode()){
                twoMap.put(menu.getName(),menu);
            }
            //第二层课程的判断
            if (!twoMap.containsKey(questions.getCourse())){
                //如果不存在，直接新增课程后两层的目录
                menuBo.setFatherId(menuEntity.getId());
                menuBo.setName(questions.getCourse());
                questionsMapper.insertMenu(menuBo);
                menuBo.setFatherId(menuBo.getOneId());
                menuBo.setName(questions.getKnowledge());
                questionsMapper.insertMenu(menuBo);
            }else{
                //如果存在，继续往下面遍历
                menuEntity = twoMap.get(questions.getCourse());
                HashMap<String, MenuEntity> thirdMap = new HashMap<>();
                for(MenuEntity menu:menuEntity.getChildNode()){
                    thirdMap.put(menu.getName(),menu);
                }
                //第三层知识点的判断
                if (!thirdMap.containsKey(questions.getKnowledge())){
                    //如果不存在，直接新增知识点属性
                    menuBo.setFatherId(menuEntity.getId());
                    menuBo.setName(questions.getKnowledge());
                    questionsMapper.insertMenu(menuBo);
                }
            }

        }
        //添加试题所有属性
        questionsMapper.insertAttribute(questions);
        //添加试题内容
        Integer count = questionsMapper.addQuestions(questions);

        //把试题添加到创建人
        questionsMapper.addMe(new PersonalBo(questions.getUserId().toString(),questions.getId().toString()));
        return null;
    }

    /**
     * @param questions
     * @Authon: chengfei
     * @Date: 2020/6/28 18:32
     * @Description: 查看试题
     */
    public List<QuestionBaseEntity> queryQuestions(QueryQuestionsBo questions) {
        questions.setFlag(2);
        return questionsMapper.queryQuestions(questions);
    }

    public QuestionAnswerEntity queryAnswer(Long questionsId) {
        return questionsMapper.queryAnswer(questionsId);
    }

    public Integer addMe(PersonalBo personal) {
        return questionsMapper.addMe(personal);
    }

    public Integer delete(Long id,String userId,String userType) {
        Integer count = 0;
        if (Long.valueOf(userType) == 1){
            count = questionsMapper.delete(id);
        }else {
            count =questionsMapper.deletePersonal(id,userId);
        }
        return count;
    }
}
