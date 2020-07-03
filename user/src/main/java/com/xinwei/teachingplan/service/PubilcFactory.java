package com.xinwei.teachingplan.service;

import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PubilcFactory
 * @Author chengfei
 * @Date 2020/7/3 13:55
 * @Version 1.0
 * @Description: TODO
 **/
@Component
public class PubilcFactory implements ApplicationContextAware {

    private  Map<String,PublicService> publicServiceMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, PublicService> map = applicationContext.getBeansOfType(PublicService.class);
        publicServiceMap.putAll(map);
    }
    public  <T extends PublicService> T getImpl(String type){
        return (T) publicServiceMap.get(type);
    }

}
