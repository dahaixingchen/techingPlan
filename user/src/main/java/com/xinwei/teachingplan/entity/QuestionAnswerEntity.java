package com.xinwei.teachingplan.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QuestionAnswer
 * @Date 2020/6/28 18:35
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class QuestionAnswerEntity {

    @ApiModelProperty(value = "id")
    private Long id;

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
    private String questionsExplainImage;
}
