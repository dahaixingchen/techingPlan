package com.xinwei.teachingplan.mapper;

import com.xinwei.teachingplan.bo.TeachBo;
import com.xinwei.teachingplan.bo.TeachPointsBo;
import com.xinwei.teachingplan.bo.TeachPracticeBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonalMapper {
    Integer updateTeach(TeachBo teachBo);

    void deletePoints(String teachId);

    void insertPoint(@Param("teachPointsList") List<TeachPointsBo> teachPointsList);

    void deletePractice(String teachId);

    void updatePractice(@Param("teachPracticeList") List<TeachPracticeBo> teachPracticeList);
}
