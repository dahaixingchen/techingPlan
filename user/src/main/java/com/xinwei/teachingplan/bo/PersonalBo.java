package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: PersonalBo
 * @Author chengfei
 * @Date 2020/7/4 17:27
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class PersonalBo {
    @ApiModelProperty(value = "个人id",required = true)
    private String userId;

    @ApiModelProperty(value = "试题id",required = true)
    private String questionsId;

    @ApiModelProperty(value = "教案id",required = true)
    private String teachId;

    @ApiModelProperty(value = "0表示查询全部，1表示在个人中心中查询")
    private Integer flag;

    public PersonalBo(String userId,String questionsId){
        this.userId = userId;
        this.questionsId = questionsId;
    }

    public PersonalBo(String userId,String teachId,String questionsId){
        this.userId = userId;
        this.teachId = teachId;
    }
}
