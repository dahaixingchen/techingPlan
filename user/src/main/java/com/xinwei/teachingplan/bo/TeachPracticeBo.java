package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: TeachPracticeBo
 * @Date 2020/7/11 13:49
 * @Description: TODO
 **/
@Data
public class TeachPracticeBo {

    @ApiModelProperty(value = "教案id")
    private String teachId;

    @ApiModelProperty(value = "练习题属于周几", required = true)
    private String week;

    @ApiModelProperty(value = "教案试题的id集合", required = true)
    private String practiceQuestionIds;

    @ApiModelProperty(value = "教案试题的id集合",required = true)
    private Map<String, Object> practiceQuestionIds_detail;

    @ApiModelProperty(value = "家长签字", required = true)
    private String signature;
}
