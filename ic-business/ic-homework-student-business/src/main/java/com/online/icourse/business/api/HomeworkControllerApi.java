package com.online.icourse.business.api;

import com.online.icourse.business.dto.request.StundetQueryHwParam;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.AnswerRecord;
import com.online.icourse.provider.bean.dto.request.AnswerRecordModify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName HomeworkControllerApi
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@Api(value = "学生作业业务",description = "学生作业业务接口")
public interface HomeworkControllerApi {
    @ApiOperation("学生查询作业列表")
    public QueryResponseResult<AnswerRecord> getStudentHomeworkList(StundetQueryHwParam query);

    @ApiOperation("学生获取某作业作答记录")
    public ResponseResult<AnswerRecord> getHomeworkAnswerRecord(String hpid);


    @ApiOperation("学生更新作答记录")
    public ResponseResult<Void> saveAnswerRecord(AnswerRecordModify answerRecordModify);
}
