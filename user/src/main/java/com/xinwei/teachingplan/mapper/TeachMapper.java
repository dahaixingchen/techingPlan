package com.xinwei.teachingplan.mapper;

import com.xinwei.teachingplan.bo.PersonalBo;
import com.xinwei.teachingplan.bo.QueryTeachBo;
import com.xinwei.teachingplan.bo.TeachBo;

import java.util.List;

public interface TeachMapper {
    Integer addTeach(TeachBo teachBo);

    void addTeachPoints(TeachBo teachBo);

    void addTeachPractice(TeachBo teachBo);

    List<TeachBo> queryTeach(QueryTeachBo teachBo);

    Integer delete(String teachId);

    Integer addMe(PersonalBo Personal);
}
