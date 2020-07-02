package com.xinwei.teachingplan.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PubilcService
 * @Author chengfei
 * @Date 2020/6/29 12:51
 * @Version 1.0
 * @Description: TODO
 **/
public interface PublicService {
    public List<String> grade(String dataType);

    public List<String> course(String dataType) ;

    public List<String> year(String dataType) ;

    List<String> label(String dataType);

    List<String> knowledge(String dataType);
}
