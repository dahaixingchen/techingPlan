package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.MenuBo;
import com.xinwei.teachingplan.bo.PersonalBo;
import com.xinwei.teachingplan.bo.QueryQuestionsBo;
import com.xinwei.teachingplan.bo.QuestionsBo;
import com.xinwei.teachingplan.entity.MenuEntity;
import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import com.xinwei.teachingplan.mapper.PublicQuestionsMapper;
import com.xinwei.teachingplan.mapper.QuestionsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: QuestionsService
 * @Author chengfei
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
    public Integer addQuestions(QuestionsBo questions) {
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
                //如果不存在，直接新增课程
                menuBo.setFatherId(menuBo.getOneId());
                menuBo.setName(questions.getCourse());
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
                    menuBo.setFatherId(menuBo.getOneId());
                    menuBo.setName(questions.getKnowledge());
                    questionsMapper.insertMenu(menuBo);
                }
            }
        }
        //添加试题所有属性
        questionsMapper.insertAttribute(questions);
        //添加试题内容
        Integer count = questionsMapper.addQuestions(questions);
        return count;
    }

    /**
     * @param questions
     * @Authon: chengfei
     * @Date: 2020/6/28 18:32
     * @Description: 查看试题
     */
    public List<QuestionBaseEntity> queryQuestions(QueryQuestionsBo questions) {
        questions.getComplexity();
        return questionsMapper.queryQuestions(questions);
    }

    public List<QuestionAnswerEntity> queryAnswer(Long questionsId) {
        return questionsMapper.queryAnswer(questionsId);
    }

    public Integer addMe(PersonalBo personal) {
        return questionsMapper.addMe(personal);
    }

    public Integer delete(Long id) {
        return null;
    }
}
