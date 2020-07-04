package com.xinwei.teachingplan.mapper;


import com.xinwei.teachingplan.bo.QuestionsBo;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;

import java.util.List;

public interface QuestionsMapper {

    Integer addQuestions(QuestionsBo questions);

    List<QuestionBaseEntity> queryQuestions(QuestionsBo questions);
}
