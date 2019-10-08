package com.online.icourse.business.controller;

import com.online.icourse.business.Dto.HomeworkPublishModify;
import com.online.icourse.business.Dto.PublishHwStuQuery;
import com.online.icourse.business.api.HomeworkControllerApi;
import com.online.icourse.common.exception.ExceptionCast;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.AnswerRecord;
import com.online.icourse.provider.bean.Homework;
import com.online.icourse.provider.bean.HomeworkPublish;
import com.online.icourse.provider.bean.dto.query.HomeworkPublishQueryDto;
import com.online.icourse.provider.bean.dto.query.HomeworkQueryDto;
import com.online.icourse.provider.service.AnswerRecordService;
import com.online.icourse.provider.service.ClazzBaseService;
import com.online.icourse.provider.service.HomeworkPublishService;
import com.online.icourse.provider.service.HomeworkService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

/**
 * @ClassName HomeworkController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@RestController
@RequestMapping("homework")
public class HomeworkController implements HomeworkControllerApi {
    @Reference(version = "2.0.0")
    private HomeworkService homeworkService;
    @Reference(version = "2.0.0")
    private HomeworkPublishService publishService;
    @Reference(version = "2.0.0")
    private ClazzBaseService clazzBaseService;
    @Reference(version = "2.0.0")
    private AnswerRecordService answerRecordService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostMapping
    @Override
    public ResponseResult<Void> createHomework(@RequestBody Homework homework) {

        DubboResponse<Void> res = homeworkService.createHomework(homework);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS("创建作业成功");
    }

    @DeleteMapping("{homeworkId}")
    @Override
    public ResponseResult<Void> deleteHomework(@PathVariable String homeworkId) {
        DubboResponse<Void> res = homeworkService.deleteHomework(homeworkId);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS("删除作业成功");
    }

    @PostMapping("publish")
    @Override
    public ResponseResult<Long> publishHomework(@RequestBody HomeworkPublish homeworkPublish) {
        //查看班级是否存在
        DubboResponse<Long> res = clazzBaseService.exsistClazz(homeworkPublish.getClazzIds());
        if (!res.isSuccess()) {
            return ResponseResult.FAIL(res.getResultCode(),res.getData());
        }
        Calendar c1 = Calendar.getInstance();
        homeworkPublish.setPublishTime(c1.getTime());
        c1.add(Calendar.DATE, 20);
        homeworkPublish.setDeadline(c1.getTime());
        //发布作业
        DubboResponse<String> res2 = publishService.publishHomework(homeworkPublish);
        if (!res2.isSuccess()) {
            ExceptionCast.cast(res2.getResultCode());
        }
        //发送Mq消息，异步创建学生作业记录，邮件通知班级学生，生成作业静态页面
        amqpTemplate.convertAndSend("homework.publish",res2.getData());

        return ResponseResult.SUCCESS("发布作业成功");
    }

    @PutMapping("publish")
    @Override
    public ResponseResult<Void> modifyPublishHomeworkTime(HomeworkPublishModify homeworkPublishModify) {
        DubboResponse<Void> res = publishService.modifyPublishHomeworkTime(homeworkPublishModify);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS("修改发布作业成功");
    }

    @GetMapping("list")
    @Override
    public QueryResponseResult<Homework> queryHomeworks(HomeworkQueryDto homeworkQueryDto) {
        DubboResponse<QueryResult<Homework>> res = homeworkService.queryHomeworks(homeworkQueryDto);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return QueryResponseResult.SUCCESS(res.getData());
    }

    @GetMapping("{homeworkId}")
    @Override
    public ResponseResult<Homework> getOneHomework(@PathVariable String homeworkId) {
        DubboResponse<Homework> res = homeworkService.getOneHomework(homeworkId);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS(res.getData());
    }

    @GetMapping("publish/list")
    @Override
    public QueryResponseResult<HomeworkPublish> queryPublishHomeworks(HomeworkPublishQueryDto homeworkPublishQueryDto) {
        DubboResponse<QueryResult<HomeworkPublish>> res = publishService.queryPublishHomeworks(homeworkPublishQueryDto);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return QueryResponseResult.SUCCESS(res.getData());
    }
    @GetMapping("/publish/{homeworkPublishId}")
    @Override
    public ResponseResult<HomeworkPublish> getOnePublishHomework(String homeworkPublishId) {
        DubboResponse<HomeworkPublish> res = publishService.getOnePublishHomework(homeworkPublishId);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS(res.getData());
    }

    @Override
    public QueryResponseResult<AnswerRecord> getOnePublishHomeworkStudentList(PublishHwStuQuery publishHwStuQuery) {
        DubboResponse<QueryResult<AnswerRecord>> res =  answerRecordService.getOnePublishHomeworkStudentList(publishHwStuQuery);
        if(!res.isSuccess()){
            ExceptionCast.cast(res.getResultCode());
        }
        return QueryResponseResult.SUCCESS(res.getData());
    }

    @Override
    public ResponseResult<AnswerRecord> getStudentAnswerRecord(String arId) {
        DubboResponse<AnswerRecord> res = answerRecordService.findById(arId);
        if(!res.isSuccess()){
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS(res.getData());
    }


}
