package com.xinwei.teaching.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QuestionsBo
 * @Author chengfei
 * @Date 2020/6/28 17:00
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class QuestionsBo {

    @ApiModelProperty(value = "试题所属年级",required = true)
    private String grade;

    @ApiModelProperty(value = "试题所属课程",required = true)
    private String course;

    @ApiModelProperty(value = "试题所属课程,1: 容易 ,2: 较易 ,3: 一般 ,4: 较难 , 5: 困难 ",required = true)
    private int complexity;

    @ApiModelProperty(value = "试题的题型,1: 单选题 ,2: 多选题 ,3: 判断题 ,4: 填空题 ,5: 解答题",required = true)
    private int questionsType;

    @ApiModelProperty(value = "试题所属年份",required = true)
    private String year;

    @ApiModelProperty(value = "试题标签",required = true)
    private String label;

    @ApiModelProperty(value = "试题的知识点",required = true)
    private String knowledge;

    @ApiModelProperty(value = "试题题干",required = true)
    private String questionsStart;

    @ApiModelProperty(value = "试题题干对应图片",required = true)
    private String questionsStartImage;

    @ApiModelProperty(value = "试题答案",required = true)
    private String questionsAnswer;

    @ApiModelProperty(value = "试题答案对应图片",required = true)
    private String questionsAnswerImage;

    @ApiModelProperty(value = "试题分析")
    private String questionsAnalyze;

    @ApiModelProperty(value = "试题分析对应图片")
    private String questionsAnalyzeImage;

    @ApiModelProperty(value = "试题点评")
    private String questionsRemark;

    @ApiModelProperty(value = "试题点评对应图片")
    private String questionsRemarkImage;

    @ApiModelProperty(value = "试题解答")
    private String questionsExplain;

    @ApiModelProperty(value = "试题解答对应图片")
    private String QuestionsExplainImage;

}
