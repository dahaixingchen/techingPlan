package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: QueryTeachBo
 * @Author chengfei
 * @Date 2020/7/2 12:54
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class QueryTeachBo {
    @ApiModelProperty(value = "id")
    private List<String> id;


    @ApiModelProperty(value = "试题所属年级,默认为全部,前端传值null")
    private List<String> grade;

    @ApiModelProperty(value = "试题所属课程,默认为全部,前端传值null")
    private List<String> course;

    @ApiModelProperty(value = "备课时间,默认为全部,前端传值null")
    private List<String> prepareLessonsTime;

    @ApiModelProperty(value = "教案课题,默认为全部,前端传值null")
    private List<String> teachTopic;
}
