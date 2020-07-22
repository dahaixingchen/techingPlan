package com.xinwei.teachingplan.bo;

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

    @ApiModelProperty(value = "用户id",required = true)
    private Long user_id;

    @ApiModelProperty(value = "试题id")
    private Long id;

    @ApiModelProperty(value = "试题所属年级,默认为全部",required = true)
    private String grade;

    @ApiModelProperty(value = "试题所属课程,默认为全部",required = true)
    private String course;

    @ApiModelProperty(value = "试题所属课程,默认为全部,1: 容易 ,2: 较易 ,3: 一般 ,4: 较难 , 5: 困难 ",required = true)
    private int complexity;

    @ApiModelProperty(value = "试题的题型,默认为全部,1: 单选题 ,2: 多选题 ,3: 判断题 ,4: 填空题 ,5: 解答题",required = true)
    private int questionsType;

    @ApiModelProperty(value = "试题所属年份,默认为全部",required = true)
    private String year;

    @ApiModelProperty(value = "试题标签,默认为全部",required = true)
    private String label;

    @ApiModelProperty(value = "试题的知识点,默认为全部",required = true)
    private String knowledge;

    @ApiModelProperty(value = "试题题干",required = true)
    private String questionsStart;

    @ApiModelProperty(value = "试题题干对应图片")
    private String questionsStartImage;

    @ApiModelProperty(value = "试题答案",required = true)
    private String questionsAnswer;

    @ApiModelProperty(value = "试题答案对应图片")
    private String questionsAnswerImage;

    @ApiModelProperty(value = "试题分析",required = true)
    private String questionsAnalyze;

    @ApiModelProperty(value = "试题分析对应图片")
    private String questionsAnalyzeImage;

    @ApiModelProperty(value = "试题点评",required = true)
    private String questionsRemark;

    @ApiModelProperty(value = "试题点评对应图片")
    private String questionsRemarkImage;

    @ApiModelProperty(value = "试题解答",required = true)
    private String questionsExplain;

    @ApiModelProperty(value = "试题解答对应图片")
    private String questionsExplainImage;

}
