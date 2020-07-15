package com.xinwei.teachingplan.test;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: 动感小⑦
 * @Date:2020/2/22 10:56
 */
public class PathUtils {
    //获取springboot项目的根目录
    public static String getRootPath() throws FileNotFoundException {
        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) path = new File("");
        System.out.println("rootPath:" + path.getAbsolutePath());
        return path.getAbsolutePath();
    }

    //获取springboot项目的static目录
    public static String getStaticPath() throws IOException {
        //获取static目录  注意部署的时候更改路径/
        String path = null;
        if (getSeparator().equals("/")) {                //linux系统
            path = getRootPath() + "/static";
        } else {
            path = getRootPath() + "\\static";
        }
        System.out.println("staticPath:" + path);
        return path;
    }

    //跨平台路径斜杠分隔符获取
    public static String getSeparator() {
        return File.separator;
    }

    // 获取文件后缀名
    public static String getSuffix(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
