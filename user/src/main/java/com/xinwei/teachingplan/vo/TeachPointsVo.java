package com.xinwei.teachingplan.vo;

import com.xinwei.teachingplan.bo.TeachPointsBo;
import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeachPoints
 * @Author chengfei
 * @Date 2020/7/11 13:48
 * @Description: TODO
 **/
@Data
public class TeachPointsVo {

    @ApiModelProperty(value = "教案id")
    private String teachId;

    @ApiModelProperty(value = "考点梳理",required = true)
    private String points;

    @ApiModelProperty(value = "剖析",required = true)
    private String analyse;

    @ApiModelProperty(value = "小试牛刀-试题集合",required = true)
    private List<QuestionAnswerEntity> pointsQuestionIdList;

    @ApiModelProperty(value = "教案试题的id集合",required = true)
    private Map<String, Map<String, Object>> pointsQuestionIds_detail;

    @ApiModelProperty(value = "小试牛刀-试题集合",required = true)
    private String pointsQuestionIds;
}
