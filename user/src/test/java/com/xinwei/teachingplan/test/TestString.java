package com.xinwei.teachingplan.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestString {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testPlus() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = s + String.valueOf(i);
        }
        long te = System.currentTimeMillis();
        logger.info("+ cost {} ms", te - ts);
    }

    @Test
    public void testConcat() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = s.concat(String.valueOf(i));
        }
        long te = System.currentTimeMillis();
        logger.info("concat cost {} ms", te - ts);
    }

    @Test
    public void testJoin() {
        List<String> list = new ArrayList<String>();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }
        StringUtils.join(list, "");
        long te = System.currentTimeMillis();
        logger.info("StringUtils.join cost {} ms", te - ts);
    }

    @Test
    public void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        logger.info("StringBuffer cost {} ms", te - ts);
    }

    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        logger.info("StringBuilder cost {} ms", te - ts);
    }

    public static void main(String[] args) throws InterruptedException {
//        StringBuilder stringBuilder = new StringBuilder();
        final StringBuffer stringBuilder = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        stringBuilder.append("a");
                    }
                }
            }).start();
        }

        Thread.sleep(100);
        System.out.println(stringBuilder.length());
    }

    @Test
    public void test() {
        String date_string = "2016-09";
// 利用java中的SimpleDateFormat类，指定日期格式，注意yyyy,MM大小写
// 这里的日期格式要求javaAPI中有详细的描述，不清楚的话可以下载相关API查看
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

// SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
// 设置日期转化成功标识
        boolean dateflag = true;
// 这里要捕获一下异常信息
        try {
            Date date = format.parse(date_string);
        } catch (ParseException e) {
            dateflag = false;
        } finally {
// 成功：true ;失败:false
            System.out.println("日期是否满足要求" + dateflag);
        }
    }
}