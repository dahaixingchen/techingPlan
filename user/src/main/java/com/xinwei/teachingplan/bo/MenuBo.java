package com.xinwei.teachingplan.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: MenuBo
 * @Author chengfei
 * @Date 2020/7/12 8:37
 * @Description: TODO
 **/
@Data
public class MenuBo {
    @ApiModelProperty(value = "第一层的id")
    private Long oneId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父id")
    private Long fatherId;

    public MenuBo(String name) {
        this.name = name;
    }

    public MenuBo() {
    }

    public MenuBo(Long fatherId) {
        this.fatherId = fatherId;
    }
}
