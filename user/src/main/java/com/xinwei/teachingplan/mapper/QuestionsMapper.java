package com.xinwei.teachingplan.mapper;


import com.xinwei.teachingplan.bo.MenuBo;
import com.xinwei.teachingplan.bo.PersonalBo;
import com.xinwei.teachingplan.bo.QueryQuestionsBo;
import com.xinwei.teachingplan.bo.QuestionsBo;
import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionsMapper {

    Integer addQuestions(QuestionsBo questions);

    List<QuestionBaseEntity> queryQuestions(QueryQuestionsBo questions);

    QuestionAnswerEntity queryAnswer(Long questionsId);

    Integer addMe(PersonalBo personal);

    void insertMenu(MenuBo menuBo);

    void insertAttribute(QuestionsBo questions);

    Integer delete(Long id);

    Integer deletePersonal(@Param("id") Long id, @Param("userId") String userId);

    Map<String, Object> queryAnswerMap(Long questionsId);
}
