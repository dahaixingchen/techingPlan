package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QueryTeachById
 * @Author chengfei
 * @Date 2020/7/14 14:08
 * @Description: TODO
 **/
@Data
public class QueryTeachById extends QueryTeachBo {
    @ApiModelProperty(value = "教案id，在查看完整教案的时候使用")
    private Long id;
}
