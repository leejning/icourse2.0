package com.online.icourse.business.api;

import com.online.icourse.business.dto.HomeworkPublishModify;
import com.online.icourse.business.dto.PublishHwStuQuery;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.AnswerRecord;
import com.online.icourse.provider.bean.Homework;
import com.online.icourse.provider.bean.HomeworkPublish;
import com.online.icourse.provider.bean.dto.query.HomeworkPublishQueryDto;
import com.online.icourse.provider.bean.dto.query.HomeworkQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName HomeworkControllerApi
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Api(value = "教师作业管理" ,description = "教师作业管理接口")
public interface HomeworkControllerApi {
    @ApiOperation("创建作业到作业库")
    public ResponseResult<Void> createHomework(Homework homework);
    @ApiOperation("从作业库删除作业")
    public ResponseResult<Void> deleteHomework(String homeworkId);
    @ApiOperation("发布作业")
    public ResponseResult<Long> publishHomework(HomeworkPublish homeworkPublish);
    @ApiOperation("修改发布作业的时间信息")
    public ResponseResult<Void> modifyPublishHomeworkTime(HomeworkPublishModify homeworkPublishModify);

    @ApiOperation("获取作业库列表")
    public QueryResponseResult<Homework> queryHomeworks(HomeworkQueryDto homeworkQueryDto);
    @ApiOperation("查看作业库中的作业的详情")
    public ResponseResult<Homework> getOneHomework(String homeworkId);

    @ApiOperation("获取已发布的作业列表")
    public QueryResponseResult<HomeworkPublish> queryPublishHomeworks(HomeworkPublishQueryDto homeworkQueryDto);
    @ApiOperation("查看已发布作业详情")
    public ResponseResult<HomeworkPublish> getOnePublishHomework(String homeworkPublishId);

    @ApiOperation("查看已发布作业相关学生列表")
    public QueryResponseResult<AnswerRecord> getOnePublishHomeworkStudentList(PublishHwStuQuery publishHwStuQuery);

    @ApiOperation("查看学生作答记录")
    ResponseResult<AnswerRecord> getStudentAnswerRecord(String arId);
}
