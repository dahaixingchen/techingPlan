package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.bo.QuestionsBo;
import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import com.xinwei.teachingplan.mapper.QuestionsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
      * @Authon: chengfei
      * @Date: 2020/6/28 18:21
      * @Description: 添加试题
      **/
    public void addQuestions(QuestionsBo questions) {
        questionsMapper.addQuestions(questions);
    }

    /**
      * @Authon: chengfei
      * @Date: 2020/6/28 18:32
      * @Description: 查看试题
      **/
    public List<QuestionBaseEntity> queryQuestions(QuestionsBo questions) {
        return null;
    }

    public List<QuestionAnswerEntity> queryAnswer(Long questionsId) {
        return null;
    }

    public void addMe(Long questionsId) {

    }
}
