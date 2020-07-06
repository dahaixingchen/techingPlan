package com.xinwei.teachingplan.mapper;

import com.xinwei.teachingplan.entity.MenuEntity;

import java.util.List;

public interface PublicQuestionsMapper {

    List<String> grade();

    List<String> course();

    List<String> year();

    List<String> label();

    List<String> knowledge();

    List<MenuEntity> menuQuery();

}
