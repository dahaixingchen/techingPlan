package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.PersonalBo;
import com.xinwei.teachingplan.bo.QueryQuestionsBo;
import com.xinwei.teachingplan.bo.QuestionsBo;
import com.xinwei.teachingplan.entity.QuestionAnswerEntity;
import com.xinwei.teachingplan.entity.QuestionBaseEntity;
import com.xinwei.teachingplan.service.QuestionsService;
import com.xinwei.teachingplan.util.ApiMessage;
import com.xinwei.teachingplan.util.MessageConstant;
import com.xinwei.teachingplan.util.PathUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @ClassName: QuestionsController
 * @Date 2020/6/28 16:39
 * @Version 1.0
 * @Description: 试卷模块
 **/
@Api("试卷模块")
@RestController
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private HttpServletRequest request;

    @Value("${img.path}")
    private String imgPath;

    @ApiOperation(value = "新增试题")
    @PostMapping("/add-questions")
    public @ResponseBody
    ApiMessage addQuestions(HttpServletRequest request, QuestionsBo questions) {
        List<MultipartFile> questionsStartImages = ((MultipartHttpServletRequest) request).getFiles("questionsStartImages");
        List<MultipartFile> questionsAnswerImages = ((MultipartHttpServletRequest) request).getFiles("questionsAnswerImages");
        List<MultipartFile> questionsAnalyzeImages = ((MultipartHttpServletRequest) request).getFiles("questionsAnalyzeImages");
        List<MultipartFile> questionsRemarkImages = ((MultipartHttpServletRequest) request).getFiles("questionsRemarkImages");
        List<MultipartFile> questionsExplainImages = ((MultipartHttpServletRequest) request).getFiles("questionsExplainImages");
        String path = null;
        try {
            //图片存储
            String startImages = this.imageDeal(questionsStartImages);
            String answerImages = this.imageDeal(questionsAnswerImages);
            String analyzeImages = this.imageDeal(questionsAnalyzeImages);
            String remarkImages = this.imageDeal(questionsRemarkImages);
            String explainImages = this.imageDeal(questionsExplainImages);
            questions.setQuestionsStartImage(startImages);
            questions.setQuestionsAnswerImage(answerImages);
            questions.setQuestionsAnalyzeImage(analyzeImages);
            questions.setQuestionsRemarkImage(remarkImages);
            questions.setQuestionsExplainImage(explainImages);

//            String userId = request.getHeader("userId");
//            questions.setUserId(Long.valueOf(userId));
            String message = questionsService.addQuestions(questions);
            if (message != null) {
                return ApiMessage.error(message);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE);
    }

    //得到图片的路径
    private String getImagesPath(List<MultipartFile> imagesMul) throws FileNotFoundException {
        String path = PathUtils.getRootPath() + File.separator + "static" + File.separator + "questions_teach_imag";
        StringBuilder images = new StringBuilder();
        for (MultipartFile image : imagesMul) {
            String imagePath = path + File.separator + image.getOriginalFilename();
            images.append(imagePath).append(",");
        }
        return images.toString();
    }

    //存储图片
    private String imageDeal(List<MultipartFile> files) throws FileNotFoundException {
        String imagesPath = new String();
        String path = imgPath + File.separator + "static" + File.separator + "questions_teach_imag";

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            if (file.isEmpty()) {
//                return "false";
            } else {
                File dest = new File(path + File.separator + fileName);
                imagesPath += fileName + ",";
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
//                    return "false";
                }
            }
        }
        return imagesPath;
    }

    @ApiOperation(value = "删除试题")
    @GetMapping("/delete")
    public ApiMessage delete(Long id) {
        String userId = request.getHeader("userId");
        String userType = request.getHeader("userType");
        Integer count = questionsService.delete(id, userId, userType);
        return ApiMessage.success(MessageConstant.DELETE_SUCCESS_MESSAGE, count);
    }

    @ApiOperation(value = "查看试题，在试题页面每点一个关键字(“期中”，“填空”，“中考试卷”" +
            "“知识点”)都会触发这个接口")
    @PostMapping("/query-questions")
    public ApiMessage<List<QuestionBaseEntity>> queryQuestions(@RequestBody QueryQuestionsBo questions) {
        List<QuestionBaseEntity> questionList = questionsService.queryQuestions(questions);
        if (questionList != null) {

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, questionList);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "查看答案")
    @ApiImplicitParam(name = "questionsId", value = "对应试题的id", required = true, paramType = "query")
    @GetMapping("/query-answer")
    public ApiMessage<QuestionAnswerEntity> queryAnswer(Long questionsId) {
        QuestionAnswerEntity answer = questionsService.queryAnswer(questionsId);
        if (answer != null) {

            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, answer);
        } else {
            return ApiMessage.error(MessageConstant.QUERY_ERROR_MESSAGE);
        }
    }

    @ApiOperation(value = "添加到我")
    @PostMapping("/add-me")
    public ApiMessage addMe(@RequestBody PersonalBo personal) {
        String userId = request.getHeader("userId");
        personal.setUserId(userId);
        Integer count = questionsService.addMe(personal);
        return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE, count);
    }

}
