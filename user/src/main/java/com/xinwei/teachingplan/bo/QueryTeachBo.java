package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: QueryTeachBo
 * @Date 2020/7/2 12:54
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class QueryTeachBo {

    @ApiModelProperty(value = "试题所属年级,默认为全部,前端传值null")
    private String grade;

    @ApiModelProperty(value = "试题所属课程,默认为全部,前端传值null")
    private String course;

    @ApiModelProperty(value = "备课时间,默认为全部,前端传值null")
    private List<String> prepareLessonsTime;

    @ApiModelProperty(value = "教案课题,默认为全部,前端传值null")
    private List<String> teachTopic;

    @ApiModelProperty(value = "0表示查询全部，1表示在个人中心中查询")
    private Integer flag;

    @ApiModelProperty(value = "只在个人中心查询的时候使用，需要传对应用户的id")
    private String userId;
}
