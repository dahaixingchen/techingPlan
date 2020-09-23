package com.xinwei.teachingplan.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MenuEntity
 * @Date 2020/7/6 12:54
 * @Version 1.0
 * @Description: TODO
 **/
@Data
public class MenuEntity {
    @ApiModelProperty(value = "id",required = true)
    private Long id;

    @ApiModelProperty(value = "类型名称",required = true)
    private String name;

    @ApiModelProperty(value = "父节点id",required = true)
    private Long fatherId;

    @ApiModelProperty(value = "试题答案",required = true)
    private List<MenuEntity> ChildNode;

    public MenuEntity(Long id, String name, Long fatherId, List<MenuEntity> menuList) {
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
        this.ChildNode = menuList;
    }

    public MenuEntity() {
    }
}
