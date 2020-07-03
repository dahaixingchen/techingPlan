package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.mapper.PublicTeachMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PublicTeachImpl
 * @Author chengfei
 * @Date 2020/7/3 11:28
 * @Version 1.0
 * @Description: TODO
 **/
@Service("teach")
public class PublicTeachImpl implements PublicService {
    @Resource
    PublicTeachMapper publicTeachMapper;

    @Override
    public String getType() {
        return "teach";
    }

    @Override
    public List<String> grade() {
        return publicTeachMapper.grade();
    }

    @Override
    public List<String> course() {
        return publicTeachMapper.course();
    }

    @Override
    public List<String> year() {
        return publicTeachMapper.year();
    }

    public List<String> teachTopic() {
        return publicTeachMapper.teachTopic();
    }
}
