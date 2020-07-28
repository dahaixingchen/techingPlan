package com.xinwei.teachingplan.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WordUtil {

    /**
     * 生成word文件通过response返回文件流
     *
     * @param dataMap      word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：model.ftl
     * @param fileName     生成的文件名称，例如：Test.doc
     */
    public static void createWordByte(Map<String, Object> dataMap, String templateName, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //创建配置实例
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
            //设置编码
            configuration.setDefaultEncoding("utf-8");
            //ftl模板文件
            configuration.setClassForTemplateLoading(WordUtil.class, "/templates");
            //获取模板
            Template template = configuration.getTemplate(templateName);
            //输出文件

            response.reset();
            response.setContentType("application/x-download");
            response.setCharacterEncoding("UTF-8");
//            response.setContentType("multipart/form-data");
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName.getBytes())
//                    , "UTF-8"));
            response.addHeader("Content-Disposition","attachment;filename=" + fileName);
            //将模板和数据模型合并生成文件
            template.process(dataMap, response.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            response.getWriter().close();
        }
    }

    /**
     * 生成word文件并返回二进制
     *
     * @param dataMap      word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：model.ftl
     * @param fileName     生成的文件名称，例如：Test.doc
     */
    public static byte[] createWordByte(Map<String, Object> dataMap, String templateName, String fileName) throws IOException {
        File outFile = null;
        Writer out = null;
        try {
            //创建配置实例
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
            //设置编码
            configuration.setDefaultEncoding("utf-8");
            //ftl模板文件
            configuration.setClassForTemplateLoading(WordUtil.class, "/templates");
            //获取模板
            Template template = configuration.getTemplate(templateName);
            //输出文件
            outFile = new File(File.separator + fileName);
            //将模板和数据模型合并生成文件
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8));
            //生成文件
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            assert out != null;
            out.flush();
            out.close();
        }
        return getWordByte(outFile);
    }

    /**
     * 根据文件生成二进制数组
     *
     * @param outFile
     * @return
     */
    private static byte[] getWordByte(File outFile) {
        byte[] wordByte = null;
        try {
            FileInputStream fis = new FileInputStream(outFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            wordByte = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordByte;
    }


    /**
     * 生成word文件
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：test.ftl
     * @param filePath 文件生成的目标路径，例如：D:/wordFile/
     * @param fileName 生成的文件名称，例如：test.doc
     */
//    @SuppressWarnings("unchecked")
    public static void createWord(Map dataMap,String templateName,String filePath,String fileName){
        try {
            //创建配置实例
            Configuration configuration = new Configuration();

            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件
            configuration.setClassForTemplateLoading(WordUtil.class,"/templates");

            //获取模板
            Template template = configuration.getTemplate(templateName);

            //输出文件
            File outFile = new File(filePath+File.separator+fileName);

            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }

            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));


            //生成文件
            template.process(dataMap, out);

            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

