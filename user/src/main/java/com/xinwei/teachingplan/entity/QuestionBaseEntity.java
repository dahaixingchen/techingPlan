package com.xinwei.teachingplan.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QuestionBaseEntity
 * @Author chengfei
 * @Date 2020/6/28 18:30
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class QuestionBaseEntity {
    @ApiModelProperty(value = "试题id",required = true)
    private Long id;

    @ApiModelProperty(value = "试题题干",required = true)
    private String questionsStart;

    @ApiModelProperty(value = "试题题干对应图片",required = true)
    private String questionsStartImage;
}
