package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: TeachBo
 * @Date 2020/6/30 11:04
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class TeachBo {

    @ApiModelProperty(value = "教案id", required = true)
    private String id;

    @ApiModelProperty(value = "教师电话(教案归属唯一性保证)", required = true)
    private String teachPhone;

    @ApiModelProperty(value = "用户id", required = true)
    private String userId;

    @ApiModelProperty(value = "教师(教案作者名称)", required = true)
    private String teachName;

    @ApiModelProperty(value = "学生", required = true)
    private String studentName;

    @ApiModelProperty(value = "备课时间", required = true)
    private String prepareLessonsTime;

    @ApiModelProperty(value = "科目", required = true)
    private String course;

    @ApiModelProperty(value = "年级", required = true)
    private String grade;

    @ApiModelProperty(value = "上课时间", required = true)
    private String lessonsTime;

    @ApiModelProperty(value = "教案课题", required = true)
    private String teachTopic;

    @ApiModelProperty(value = "课型", required = true)
    private String teachType;

    @ApiModelProperty(value = "课题出处", required = true)
    private String teachSource;

    @ApiModelProperty(value = "学生主要问题", required = true)
    private String studentQuestions;

    @ApiModelProperty(value = "教学目标", required = true)
    private String teachGoal;

    @ApiModelProperty(value = "教学重点与难点", required = true)
    private String teachKeyDifficulty;

    @ApiModelProperty(value = "温故知新-复习", required = true)
    private String review;

    @ApiModelProperty(value = "考点突破-重点", required = true)
    private String keyPoint;

    @ApiModelProperty(value = "教案中考演练试题的id集合", required = true)
    private String seniorQuestionIds;

    @ApiModelProperty(value = "总结归纳", required = true)
    private String conclude;

    @ApiModelProperty(value = "自我巩固", required = true)
    private String consolidate;

    @ApiModelProperty(value = "笔记", required = true)
    private String node;

    @ApiModelProperty(value = "自我的试题ids", required = true)
    private String consolidateQuestionIds;

    @ApiModelProperty(value = "教案id", required = true)
    private String teachId;

    @ApiModelProperty(value = "教案id", required = true)
    private String teach_id;

    @ApiModelProperty(value = "考点集合", required = true)
    private List<TeachPointsBo> teachPointsList;

    @ApiModelProperty(value = "练习题集合", required = true)
    private List<TeachPracticeBo> teachPracticeList;

}
