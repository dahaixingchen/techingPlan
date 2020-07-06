package com.xinwei.teachingplan.mapper;

import com.xinwei.teachingplan.entity.MenuEntity;

import java.util.List;

public interface PublicTeachMapper {
    List<String> grade();

    List<String> course();

    List<String> year();

    List<String> teachTopic();

    List<MenuEntity> menuQuery();

    List<String> prepareLessonsTime();
}
