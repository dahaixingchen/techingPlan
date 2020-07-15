package com.xinwei.teachingplan.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.word.WordExportUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: DocUtils
 * @Author chengfei
 * @Date 2020/7/14 16:35
 * @Description: TODO
 **/
public class DocUtils {
    /**
     * 向word模板中插入数据，生成新word
     * 切记这里的path是模板路径,modelPath 是word模板的路径，outPutPath是输出路径
     * 请注意导入的model是doc导出的就是doc 如果设置成docx就会错误，docx导出的是docx
     *
     * @param map
     * @param modelPath
     * @throws Exception
     */
    public static void generateWord(HashMap map, String modelPath, String outPutPath) throws Exception {
        //因为空格无法输出，过滤一下空格 用-代替
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            if (val.equals("") || val == null) {
                map.put((String) key, "-");         //空格无法输出
            }
        }

        //先创建文件
        File file = new File(outPutPath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try {
            //获取文件后缀
            String suffix = PathUtils.getSuffix(modelPath);
            if (suffix.equals("docx")) {
                XWPFDocument doc = WordExportUtil.exportWord07(modelPath, map);
                FileOutputStream fos = new FileOutputStream(file);
                doc.write(fos);
                fos.close();
            } else if (suffix.equals("doc")) {
                HWPFDocument docu = new HWPFDocument(new FileInputStream(modelPath));
                Range range = docu.getRange();
                getRunge(range, map);
                FileOutputStream stream = new FileOutputStream(file);
                docu.write(stream);
                stream.flush();
                stream.close();
            }
        } catch (Exception e) {
            System.out.println("文件后缀名有误！");
            e.printStackTrace();
        }
    }

    public static void getRunge(Range range, Map<String, Object> map) {
        TableIterator tableIter = new TableIterator(range);
        Table table;
        TableRow row;
        TableCell cell;
        while (tableIter.hasNext()) {
            table = tableIter.next();
            int rowNum = table.numRows();
            for (int j = 0; j < rowNum; j++) {
                row = table.getRow(j);
                int cellNum = row.numCells();
                for (int k = 0; k < cellNum; k++) {
                    cell = row.getCell(k);
                    String container = cell.text().trim();
                    if (container.indexOf("{{") != -1 && container.indexOf("}}") != -1) {
                        String s = parseString(container, map);
                        cell.replaceText(container, s);
                    }
                }
            }
        }
    }

    public static String parseString(String container, Map<String, Object> map) {
        if (container.indexOf("{{") != -1 && container.indexOf("}}") != -1) {
            String code = container.substring(container.indexOf("{{") + 2, container.indexOf("}}"));
            if (map.containsKey(code)) {
                String s = StringUtils.replace(container, "{{" + code + "}}", map.get(code).toString());
                return parseString(s, map);
            }
        }
        return container;
    }

}
