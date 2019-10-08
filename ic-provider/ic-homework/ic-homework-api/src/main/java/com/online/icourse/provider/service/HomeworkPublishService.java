package com.online.icourse.provider.service;

import com.online.icourse.business.Dto.HomeworkPublishModify;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.HomeworkPublish;
import com.online.icourse.provider.bean.dto.query.HomeworkPublishQueryDto;

/**
 * @ClassName HomeworkPublishService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
public interface HomeworkPublishService {
    DubboResponse<String> publishHomework(HomeworkPublish homeworkPublish);

    DubboResponse<Void> modifyPublishHomeworkTime(HomeworkPublishModify homeworkPublishModify);

    DubboResponse<QueryResult<HomeworkPublish>> queryPublishHomeworks(HomeworkPublishQueryDto homeworkPublishQueryDto);

    DubboResponse<HomeworkPublish> getOnePublishHomework(String homeworkPublishId);


}
