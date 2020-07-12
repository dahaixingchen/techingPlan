package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: QueryQuestionsBo
 * @Author chengfei
 * @Date 2020/7/5 17:53
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class QueryQuestionsBo {

    @ApiModelProperty(value = "试题所属年级,默认为全部,前端传值null")
    private String grade;

    @ApiModelProperty(value = "试题所属课程,默认为全部,前端传值null",required = true)
    private String course;

    @ApiModelProperty(value = "试题的知识点,默认为全部,前端传值null",required = true)
    private String knowledge;

    @ApiModelProperty(value = "试题所属课程,默认为全部,前端传值null,1: 容易 ,2: 较易 ,3: 一般 ,4: 较难 , 5: 困难 ",required = true)
    private List<Integer> complexity;

    @ApiModelProperty(value = "试题的题型,默认为全部,前端传值null,1: 单选题 ,2: 多选题 ,3: 判断题 ,4: 填空题 ,5: 解答题",required = true)
    private List<Integer> questionsType;

    @ApiModelProperty(value = "试题所属年份,默认为全部,前端传值null",required = true)
    private List<String> year;

    @ApiModelProperty(value = "试题标签,默认为全部,前端传值null",required = true)
    private List<String> label;

    @ApiModelProperty(value = "0表示查询全部，1表示在个人中心中查询")
    private Integer flag;

    @ApiModelProperty(value = "只在个人中心查询的时候使用，需要传对应用户的id")
    private Long userId;

}
