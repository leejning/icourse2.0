package com.online.icourse.business.controller;

import com.online.icource.business.oauth.common.Oauth2Util;
import com.online.icourse.business.api.HomeworkControllerApi;
import com.online.icourse.business.dto.request.StundetQueryHwParam;
import com.online.icourse.common.exception.ExceptionCast;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.AnswerRecord;
import com.online.icourse.provider.bean.dto.request.AnswerRecordModify;
import com.online.icourse.provider.bean.dto.query.AnswerRecordQueryDto;
import com.online.icourse.provider.service.AnswerRecordService;
import com.online.icourse.provider.service.HomeworkPublishService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HomeworkController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@RestController
public class HomeworkController implements HomeworkControllerApi {
    @Reference(version = "2.0.0")
    private HomeworkPublishService homeworkPublishService;
    @Reference(version = "2.0.0")
    private AnswerRecordService answerRecordService;

//    @Autowired
    private HttpServletRequest request;


    @GetMapping("homework/list")
    @Override
    public QueryResponseResult<AnswerRecord> getStudentHomeworkList(StundetQueryHwParam query) {
        AnswerRecordQueryDto queryDto = new AnswerRecordQueryDto();
        BeanUtils.copyProperties(query,queryDto);
        DubboResponse<QueryResult<AnswerRecord>> res = answerRecordService.getStudentHomeworkList(queryDto);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return QueryResponseResult.SUCCESS(res.getData());
    }

    @GetMapping("answerecord/{hpid}")
    @Override
    public ResponseResult<AnswerRecord> getHomeworkAnswerRecord(@PathVariable String hpid) {
        Long studentId = Oauth2Util.getLoginStuId(request);
        DubboResponse<AnswerRecord> res = answerRecordService.getHomeworkAnswerRecord(hpid,studentId);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS(res.getData());
    }

    @PutMapping("answerecord")
    @Override
    public ResponseResult<Void> saveAnswerRecord(@RequestBody AnswerRecordModify answerRecordModify) {
        Long studentId = Oauth2Util.getLoginStuId(request);
        DubboResponse<Void> res = answerRecordService.saveAnswerRecord(answerRecordModify);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS();
    }
}
