package com.xinwei.teaching.service;

import com.xinwei.teaching.bo.TeachBo;
import com.xinwei.teaching.mapper.TeachMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: TeachService
 * @Author chengfei
 * @Date 2020/6/29 14:37
 * @Version 1.0
 * @Description: TODO
 **/
@Service
public class TeachService {

    @Resource
    private TeachMapper teachMapper;

    public void addTeach(TeachBo teachBo) {
    }

    public List<String> queryTeach(TeachBo teachBo) {
        return null;
    }

    public void updateTeach(TeachBo teachBo) {

    }
}
