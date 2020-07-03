package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.mapper.PublicQuestionsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PublicQuestionsImpl
 * @Author chengfei
 * @Date 2020/7/3 11:13
 * @Version 1.0
 * @Description: TODO
 **/
@Service("questions")
public class PublicQuestionsImpl implements PublicService {

    @Resource
    PublicQuestionsMapper publicQuestionsMapper;

    @Override
    public String getType() {
        return "questions";
    }

    @Override
    public List<String> grade() {
        return publicQuestionsMapper.grade();
    }

    @Override
    public List<String> course() {
        return publicQuestionsMapper.course();
    }

    @Override
    public List<String> year() {
        return publicQuestionsMapper.year();
    }

    public List<String> label() {
        return publicQuestionsMapper.label();
    }

    public List<String> knowledge() {
        return publicQuestionsMapper.knowledge();
    }
}
