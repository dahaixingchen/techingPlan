package com.xinwei.teachingplan.bo;

import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeachPoints
 * @Date 2020/7/11 13:48
 * @Description: TODO
 **/
@Data
public class TeachPointsBo {

    @ApiModelProperty(value = "教案id")
    private String teachId;

    @ApiModelProperty(value = "考点梳理", required = true)
    private String points;

    @ApiModelProperty(value = "剖析", required = true)
    private String analyse;

    @ApiModelProperty(value = "小试牛刀-试题集合", required = true)
    private String pointsQuestionIds;

    @ApiModelProperty(value = "小试牛刀-试题集合明细", required = true)
    private Map<String, Object> pointsQuestionIds_detail;
}
