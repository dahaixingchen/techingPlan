package com.xinwei.teachingplan.controller;

import com.xinwei.teachingplan.bo.PersonalBo;
import com.xinwei.teachingplan.bo.QueryTeachBo;
import com.xinwei.teachingplan.bo.TeachBo;
import com.xinwei.teachingplan.service.TeachService;
import com.xinwei.teachingplan.service.WordAction;
import com.xinwei.teachingplan.util.ApiMessage;
import com.xinwei.teachingplan.util.MessageConstant;
import com.xinwei.teachingplan.vo.TeachVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName: TeachController
 * @Author chengfei
 * @Date 2020/6/29 12:49
 * @Version 1.0
 * @Description: TODO
 **/
@Api("教案模块")
@RestController
@RequestMapping("/teach")
public class TeachController {

    @Autowired
    private TeachService teachService;

    @ApiOperation(value = "新增教案")
    @PostMapping("/add-teach")
    public ApiMessage<Integer> addTeach(@RequestBody TeachBo teachBo) {
        String message = teachService.addTeach(teachBo);
        if (message != null){
            return ApiMessage.error(message);
        }
        return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE);
    }


    @ApiOperation(value = "查询教案，在教案页面（个人中心的教案页面）没点一个关键字(“期中”，“填空”，“中考试卷”" +
            " ，“约分”，“知识点”)都会触发这个接口")
    @PostMapping("/query-teach")
    public ApiMessage<List<TeachBo>> queryTeach(@RequestBody QueryTeachBo queryTeachBo) {
        List<TeachBo> Teachs = teachService.queryTeach(queryTeachBo);
        if (Teachs != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, Teachs);
        } else {
            return ApiMessage.success(MessageConstant.QUERY_ERROR_MESSAGE);

        }
    }

    @ApiOperation(value = "完整查看，查询教案，在教案页面（个人中心的教案页面）")
    @GetMapping("/query-allTeach")
    public ApiMessage<TeachVo> queryAllTeach(Long id) {
        TeachVo Teachs = teachService.queryAllTeach(id);
        if (Teachs != null) {
            return ApiMessage.success(MessageConstant.QUERY_SUCCESS_MESSAGE, Teachs);
        } else {
            return ApiMessage.success(MessageConstant.QUERY_ERROR_MESSAGE);

        }
    }

    @ApiOperation(value = "删除教案")
    @GetMapping("/delete")
    public ApiMessage<Integer> delete(String teachId) {
        Integer count = teachService.delete(teachId);
        return ApiMessage.success(MessageConstant.DELETE_SUCCESS_MESSAGE, "共删除数据 " + count + " 条");
    }

    @ApiOperation(value = "添加到我")
    @PostMapping("/add-me")
    public ApiMessage addMe(@RequestBody PersonalBo personal) {
        Integer count = teachService.addMe(personal);
        return ApiMessage.success(MessageConstant.ADD_SUCCESS_MESSAGE, count);

    }


    @Autowired
    WordAction wordAction;

    @ApiOperation(value = "下载教案(在个人中心和教案模块都有此接口),成Word文档的形式")
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(Long teachId) throws UnsupportedEncodingException {
        byte[] bytes = wordAction.dowloadWord(teachId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode("teachWord.doc", "utf-8"));
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "测试用，下载教案到某个目录")
    @GetMapping("/getWord")
    public void getWord(Long teachId) {
        wordAction.createWord();
    }
}
