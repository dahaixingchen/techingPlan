package com.xinwei.teachingplan.mapper;

import java.util.List;

public interface PublicTeachMapper {
    List<String> grade();

    List<String> course();

    List<String> year();

    List<String> teachTopic();
}
