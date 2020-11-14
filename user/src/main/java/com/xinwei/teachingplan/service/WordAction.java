package com.xinwei.teachingplan.service;

import com.xinwei.teachingplan.mapper.TeachMapper;
import com.xinwei.teachingplan.util.WordUtil;
import com.xinwei.teachingplan.vo.TeachVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WordAction {

    private static final String[] bigWord = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十",};

    @Resource
    TeachService teachService;

    /**
     * 下载生成的word文档
     *
     * @param teachId
     * @param request
     * @param response
     * @return
     */
    public void dowloadWord(Long teachId, HttpServletRequest request, HttpServletResponse response) {

        TeachVo teachVo = teachService.queryAllTeachDownload(teachId);

        /** 用于组装word页面需要的数据 */
        Map<String, Object> dataMap = new HashMap<String, Object>();
        /** 组装数据 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        dataMap.put("prepareLessonsTime", teachVo.getPrepareLessonsTime());
        dataMap.put("lessonsTime", teachVo.getLessonsTime());
        dataMap.put("grade", teachVo.getGrade());
        dataMap.put("studentName", teachVo.getStudentName());
        dataMap.put("course", teachVo.getCourse());
        dataMap.put("teachName", teachVo.getTeachName());
        dataMap.put("teachTopic", teachVo.getTeachTopic());
        dataMap.put("teachType", teachVo.getTeachType());
        dataMap.put("teachSource", teachVo.getTeachSource());
        dataMap.put("teachGoal", teachVo.getTeachGoal());
        dataMap.put("teachKeyDifficulty", teachVo.getTeachKeyDifficulty());
        dataMap.put("review", teachVo.getReview());
        dataMap.put("keyPoint", teachVo.getKeyPoint());
        dataMap.put("studentQuestions", teachVo.getStudentQuestions());
        dataMap.put("conclude", teachVo.getConclude());
        dataMap.put("consolidate", teachVo.getConsolidate());
        dataMap.put("node", teachVo.getNode());

        List<Map<String, Object>> teachPointsList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> pointsQuestionIdList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> seniorQuestionList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> consolidateQuestionList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> practiceQuestionIdList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> teachPracticeList = new ArrayList<Map<String, Object>>();
        if (teachVo.getTeachPracticeList() != null && teachVo.getTeachPracticeList().size() > 0) {
            for (int i = 0; i < teachVo.getTeachPracticeList().size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("week", bigWord[i]);
                map.put("signature", teachVo.getTeachPracticeList().get(i).getSignature());
                teachPracticeList.add(map);
                teachVo.getTeachPracticeList().get(i).getPracticeQuestionIdList().forEach(question -> {
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("questionsStart", question.getQuestionsStart());
                    map1.put("questionsAnswer", question.getQuestionsAnswer());
                    map1.put("questionsRemark", question.getQuestionsRemark());
                    map1.put("questionsExplain", question.getQuestionsExplain());
                    map1.put("questionsAnalyze", question.getQuestionsAnalyze());
                    practiceQuestionIdList.add(map1);
                });
            }
        }
        dataMap.put("teachPracticeList", teachPracticeList);
        dataMap.put("practiceQuestionIdList", practiceQuestionIdList);
        teachVo.getConsolidateQuestionList().forEach(conslidate -> {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("questionsStart", conslidate.getQuestionsStart());
            map.put("questionsAnswer", conslidate.getQuestionsAnswer());
            map.put("questionsRemark", conslidate.getQuestionsRemark());
            map.put("questionsExplain", conslidate.getQuestionsExplain());
            map.put("questionsAnalyze", conslidate.getQuestionsAnalyze());
            consolidateQuestionList.add(map);
        });
        dataMap.put("consolidateQuestionList", consolidateQuestionList);
        teachVo.getSeniorQuestionList().forEach(senior -> {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("questionsStart", senior.getQuestionsStart());
            map.put("questionsAnswer", senior.getQuestionsAnswer());
            map.put("questionsRemark", senior.getQuestionsRemark());
            map.put("questionsExplain", senior.getQuestionsExplain());
            map.put("questionsAnalyze", senior.getQuestionsAnalyze());
            seniorQuestionList.add(map);
        });
        dataMap.put("seniorQuestionList", seniorQuestionList);

        for (int i = 0; i < teachVo.getTeachPointsList().size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("num", bigWord[i]);
            map.put("points", teachVo.getTeachPointsList().get(i).getPoints());
            map.put("analyse", teachVo.getTeachPointsList().get(i).getAnalyse());
            teachPointsList.add(map);
            teachVo.getTeachPointsList().get(i).getPointsQuestionIdList().forEach(point -> {
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("questionsStart", point.getQuestionsStart());
                map1.put("questionsAnswer", point.getQuestionsAnswer());
                map1.put("questionsRemark", point.getQuestionsRemark());
                map1.put("questionsExplain", point.getQuestionsExplain());
                map1.put("questionsAnalyze", point.getQuestionsAnalyze());
                pointsQuestionIdList.add(map1);
            });
        }
        dataMap.put("teachPointsList", teachPointsList);
        dataMap.put("pointsQuestionIdList", pointsQuestionIdList);

        byte[] fileByte = null;
        try {
            WordUtil.createWordByte(dataMap, "teachWord.ftl", "teachWord.doc", request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String createWord() {
        /** 用于组装word页面需要的数据 */
        Map<String, Object> dataMap = new HashMap<String, Object>();

        /** 组装数据 */
        dataMap.put("userName", "seawater");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        dataMap.put("prepareLessonsTime", sdf.format(new Date()));
        dataMap.put("lessonsTime", sdf.format(new Date()));
        dataMap.put("content", "freeMark生成Word文档段落内容");
        dataMap.put("grade", "高三");
        dataMap.put("studentName", "程同学");
        dataMap.put("course", "物理");
        dataMap.put("teachName", "李老师");
        dataMap.put("teachTopic", "化学真卷");
        dataMap.put("teachType", "上课的形式");
        dataMap.put("teachSource", "2018年全国卷1");
        dataMap.put("teachGoal", "让学生们完全弄清楚这个问题");
        dataMap.put("teachKeyDifficulty", "有机化学");
        dataMap.put("review", "有机化合物");
        dataMap.put("keyPoint", "糖的合成");
        dataMap.put("studentQuestions", "有机化合物");
        dataMap.put("conclude", "free发麻总结归纳");
        dataMap.put("consolidate", "自我巩固的试题阶段");
        dataMap.put("node", "这个是笔记阶段");

        List<Map<String, Object>> teachPointsList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> pointsQuestionIdList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> seniorQuestionList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> consolidateQuestionList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> practiceQuestionIdList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> teachPracticeList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("week", i);
            map.put("signature", "学生家长签字");
            teachPracticeList.add(map);
        }
        dataMap.put("teachPracticeList", teachPracticeList);
        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("questionsStart", "练习题干" + i);
            map.put("questionsAnswer", "练习答案" + i);
            map.put("questionsRemark", "练习点评" + i);
            map.put("questionsExplain", "练习解答" + i);
            map.put("questionsAnalyze", "练习分析" + i);
            practiceQuestionIdList.add(map);

        }
        dataMap.put("practiceQuestionIdList", practiceQuestionIdList);
        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("questionsStart", "自我巩固题干" + i);
            map.put("questionsAnswer", "自我巩固答案" + i);
            map.put("questionsRemark", "自我巩固点评" + i);
            map.put("questionsExplain", "自我巩固解答" + i);
            map.put("questionsAnalyze", "自我巩固分析" + i);
            consolidateQuestionList.add(map);

        }
        dataMap.put("consolidateQuestionList", consolidateQuestionList);
        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("questionsStart", "考高试题题干" + i);
            map.put("questionsAnswer", "考高试题答案" + i);
            map.put("questionsRemark", "考高试题点评" + i);
            map.put("questionsExplain", "考高试题解答" + i);
            map.put("questionsAnalyze", "考高试题分析" + i);
            seniorQuestionList.add(map);

        }
        dataMap.put("seniorQuestionList", seniorQuestionList);
        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("questionsStart", "题干" + i);
            map.put("questionsAnswer", "答案" + i);
            map.put("questionsRemark", "点评" + i);
            map.put("questionsExplain", "解答" + i);
            map.put("questionsAnalyze", "分析" + i);
            pointsQuestionIdList.add(map);
        }
        for (int i = 1; i <= 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("num", i + 1);
            map.put("points", "考点梳理" + i);
            map.put("analyse", "案例分析" + i);
            teachPointsList.add(map);
        }
        dataMap.put("teachPointsList", teachPointsList);
        dataMap.put("pointsQuestionIdList", pointsQuestionIdList);

        /** 文件名称，唯一字符串 */
        Random r = new Random();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        StringBuffer sb = new StringBuffer();
        sb.append(sdf1.format(new Date()));
        sb.append("_");
        sb.append(r.nextInt(100));

        //文件路径
        String filePath = "D:/doc_f/";

        //文件唯一名称
        String fileOnlyName = "用freemarker生成Word文档_" + sb + ".doc";

        //文件名称
        String fileName = "用freemarker生成Word文档.doc";

        /** 生成word  testWord.ftl */
        WordUtil.createWord(dataMap, "teachWord.ftl", filePath, fileOnlyName);

        return "createWordSuccess";
    }

}

