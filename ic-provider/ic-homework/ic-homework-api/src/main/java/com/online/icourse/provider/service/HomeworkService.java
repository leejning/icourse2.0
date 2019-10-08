package com.online.icourse.provider.service;

import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.Homework;
import com.online.icourse.provider.bean.dto.query.HomeworkQueryDto;

/**
 * @ClassName HomeworkService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
public interface HomeworkService {
    DubboResponse<Void> createHomework(Homework homework);

    DubboResponse<Void> deleteHomework(String homeworkId);

    DubboResponse<QueryResult<Homework>> queryHomeworks(HomeworkQueryDto homeworkQueryDto);

    DubboResponse<Homework> getOneHomework(String homeworkId);
}
