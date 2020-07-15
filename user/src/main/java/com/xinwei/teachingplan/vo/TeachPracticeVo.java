package com.xinwei.teachingplan.vo;

import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: TeachPracticeBo
 * @Author chengfei
 * @Date 2020/7/11 13:49
 * @Description: TODO
 **/
@Data
public class TeachPracticeVo {

    @ApiModelProperty(value = "教案id")
    private String teachId;

    @ApiModelProperty(value = "练习题属于周几", required = true)
    private String week;

    @ApiModelProperty(value = "教案试题的id集合", required = true)
    private List<QuestionAnswerEntity> practiceQuestionIdList;

    @ApiModelProperty(value = "教案试题的id集合", required = true)
    private String practiceQuestionIds;

    @ApiModelProperty(value = "家长签字", required = true)
    private String signature;
}
