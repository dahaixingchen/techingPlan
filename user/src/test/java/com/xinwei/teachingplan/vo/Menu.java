package com.xinwei.teachingplan.vo;

import lombok.Data;

@Data
public class Menu {

    private Integer id;

    private String name;

    private String url;

    private Integer fatherId;


    public Menu(Integer id, String name, String url, Integer fatherId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.fatherId = fatherId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", fatherId=" + fatherId +
                '}';
    }
}