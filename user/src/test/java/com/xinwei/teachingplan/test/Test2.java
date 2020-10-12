package com.xinwei.teachingplan.test;

import com.xinwei.teachingplan.service.PublicQuestionsImpl;

public class Test2 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {

        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);

        Class<?> c1 = Class.forName("com.xinwei.teachingplan.service.PublicQuestionsImpl");
        PublicQuestionsImpl o = (PublicQuestionsImpl)c1.newInstance();
        System.out.println(o.getType());
    }
}
