package com.xinwei.teaching.service;

import java.util.List;

/**
 * @ClassName: PubilcService
 * @Author chengfei
 * @Date 2020/6/29 12:51
 * @Version 1.0
 * @Description: TODO
 **/
public interface PublicService {
    public List<String> grade(String grade);

    public List<String> course(String course) ;

    public List<String> year(String yearType) ;

    List<String> label(String label);

    List<String> knowledge(String knowledge);
}