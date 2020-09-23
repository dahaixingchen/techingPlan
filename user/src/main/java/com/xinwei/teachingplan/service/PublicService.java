package com.xinwei.teachingplan.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PubilcService
 * @Date 2020/6/29 12:51
 * @Version 1.0
 * @Description: TODO
 **/
public interface PublicService {

    String getType();

    List<String> grade();

    List<String> course() ;

    List<String> year() ;

}
