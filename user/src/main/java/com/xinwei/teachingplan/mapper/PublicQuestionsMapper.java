package com.xinwei.teachingplan.mapper;

import java.util.List;

public interface PublicQuestionsMapper {

    List<String> grade();

    List<String> course();

    List<String> year();

    List<String> label();

    List<String> knowledge();
}
