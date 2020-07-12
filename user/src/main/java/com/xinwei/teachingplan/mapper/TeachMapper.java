package com.xinwei.teachingplan.mapper;

import com.xinwei.teachingplan.bo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachMapper {
    Integer addTeach(TeachBo teachBo);

    void addTeachPoints(@Param("TeachPointsList") List<TeachPointsBo> TeachPointsList);

    void addTeachPractice(@Param("TeachPracticeList") List<TeachPracticeBo> TeachPracticeList);

    List<TeachBo> queryTeach(QueryTeachBo teachBo);

    Integer delete(String teachId);

    Integer addMe(PersonalBo Personal);

    List<TeachPointsBo> queryPoint(Long id);

    List<TeachPracticeBo> getPractice(Long id);

}
