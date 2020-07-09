package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: TeachBo
 * @Author chengfei
 * @Date 2020/6/30 11:04
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class TeachBo {

    @ApiModelProperty(value = "教案id",required = true)
    private String id;

    @ApiModelProperty(value = "教师电话(教案归属唯一性保证)",required = true)
    private String teachPhone;

    @ApiModelProperty(value = "教师(教案作者名称)",required = true)
    private String teachName;

    @ApiModelProperty(value = "备课时间",required = true)
    private String prepareLessonsTime;

    @ApiModelProperty(value = "教案所属课程",required = true)
    private String course;

    @ApiModelProperty(value = "学生名称",required = true)
    private String studentName;

    @ApiModelProperty(value = "教案所属年级",required = true)
    private String grade;

    @ApiModelProperty(value = "上课时间",required = true)
    private String lessonsTime;

    @ApiModelProperty(value = "教案课题",required = true)
    private String teachTopic;

    @ApiModelProperty(value = "教案课型",required = true)
    private String teachType;

    @ApiModelProperty(value = "课题出处",required = true)
    private String teachSource;

    @ApiModelProperty(value = "学生主要问题",required = true)
    private String studentQuestions;

    @ApiModelProperty(value = "教学目标",required = true)
    private String teachGoal;

    @ApiModelProperty(value = "教学重点与难点",required = true)
    private String teachKeyDifficulty;

    @ApiModelProperty(value = "温故知新-复习",required = true)
    private String review;

    @ApiModelProperty(value = "考点突破-重点",required = true)
    private String keyPoint;

    @ApiModelProperty(value = "教案中考演练试题的id集合",required = true)
    private String seniorQuestionIds;

    @ApiModelProperty(value = "总结归纳",required = true)
    private String conclude;

    @ApiModelProperty(value = "自我巩固",required = true)
    private String consolidate;

    @ApiModelProperty(value = "教案id")
    private String teachId;

    @ApiModelProperty(value = "考点梳理",required = true)
    private String points;

    @ApiModelProperty(value = "剖析",required = true)
    private String analyse;

    @ApiModelProperty(value = "小试牛刀-试题集合",required = true)
    private String pointsQuestionIds;

    @ApiModelProperty(value = "练习题属于周几",required = true)
    private String week;

    @ApiModelProperty(value = "教案试题的id集合",required = true)
    private String practiceQuestionIds;

    @ApiModelProperty(value = "家长签字",required = true)
    private String signature;

    @ApiModelProperty(value = "是否查看全部，默认传0表示不看全部,只在查询教案的接口中使用")
    private Integer isAll;
}
