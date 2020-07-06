package com.xinwei.teachingplan.vo;

import lombok.Data;

import java.util.List;
@Data
public class MenuVo {
    private Integer id;

    private String name;

    private String url;

    private Integer fatherId;

    private List<MenuVo>  childNode;

    @Override
    public String toString() {
        return "MenuVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", fatherId=" + fatherId +
                ", childNode=" + childNode +
                '}';
    }

    public MenuVo() {
    }

    public MenuVo(Integer id, String name, String url, Integer fatherId, List<MenuVo> childNode) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.fatherId = fatherId;
        this.childNode = childNode;
    }
}