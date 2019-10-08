package com.online.icourse.provider.service;

import com.online.icourse.business.Dto.PublishHwStuQuery;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.AnswerRecord;
import com.online.icourse.provider.bean.dto.request.AnswerRecordModify;
import com.online.icourse.provider.bean.dto.query.AnswerRecordQueryDto;

/**
 * @ClassName AnswerRecordService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
public interface AnswerRecordService {
    DubboResponse<AnswerRecord> getHomeworkAnswerRecord(String hpid, Long studentId);

    DubboResponse<Void> saveAnswerRecord(AnswerRecordModify answerRecordModify);

    void afterPublishHomeworkCreateRecord(String hpId);

    DubboResponse<QueryResult<AnswerRecord>> getOnePublishHomeworkStudentList(PublishHwStuQuery publishHwStuQuery);

    DubboResponse<AnswerRecord> findById(String arId);

    DubboResponse<QueryResult<AnswerRecord>> getStudentHomeworkList(AnswerRecordQueryDto queryDto);
}
