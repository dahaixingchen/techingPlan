package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: TeachPoints
 * @Author chengfei
 * @Date 2020/7/11 13:48
 * @Description: TODO
 **/
@Data
public class TeachPointsBo {

    @ApiModelProperty(value = "教案id")
    private String teachId;

    @ApiModelProperty(value = "考点梳理",required = true)
    private String points;

    @ApiModelProperty(value = "剖析",required = true)
    private String analyse;

    @ApiModelProperty(value = "小试牛刀-试题集合",required = true)
    private String pointsQuestionIds;
}
