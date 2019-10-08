package com.online.icourse.provider.service;

import com.online.icourse.business.Dto.HomeworkPublishModify;
import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.response.CommonCode;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.HomeworkPublish;
import com.online.icourse.provider.bean.dto.query.HomeworkPublishQueryDto;
import com.online.icourse.provider.common.HomeworkCode;
import com.online.icourse.provider.dao.HomeworkPublishDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @ClassName HomeworkPublishServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class HomeworkPublishServiceImpl implements HomeworkPublishService {
    @Autowired
    private HomeworkPublishDao homeworkPublishDao;


    /**
     * 教师发布作业
     * @param homeworkPublish
     * @return
     */
    @Override
    public DubboResponse<String> publishHomework(HomeworkPublish homeworkPublish) {
        homeworkPublishDao.save(homeworkPublish);
        return DubboResponse.SUCCESS(homeworkPublish.getId());
    }

    /**
     * 教师修改发布作业信息
     * @param homeworkPublishModify
     * @return
     */
    @Override
    public DubboResponse<Void> modifyPublishHomeworkTime(HomeworkPublishModify homeworkPublishModify) {
        return DubboResponse.FAIL(CommonCode.TO_BE_CONTINUE);
    }

    /**
     * 教师获取发布作业列表
     * @param homeworkPublishQueryDto
     * @return
     */
    @Override
    public DubboResponse<QueryResult<HomeworkPublish>> queryPublishHomeworks(HomeworkPublishQueryDto homeworkPublishQueryDto) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(homeworkPublishQueryDto);
        Query query = HomeworkPublishQueryDto.getWhere(homeworkPublishQueryDto);
        Page<HomeworkPublish> data = homeworkPublishDao.findAll(query, pageAndSortObj.getPageable(), HomeworkPublish.class);
        QueryResult<HomeworkPublish> res = DataJpaPageUtils.setQueryResult(data, homeworkPublishQueryDto);
        return DubboResponse.SUCCESS(res);
    }

    /**
     * 教师查询发布作业详情
     * @param homeworkPublishId
     * @return
     */
    @Override
    public DubboResponse<HomeworkPublish> getOnePublishHomework(String homeworkPublishId) {
        if(!homeworkPublishDao.existsById(homeworkPublishId)){
            return DubboResponse.FAIL(HomeworkCode.NOT_EXSIST_PUBLISH_HOMEWORK_IN_THIS_ID);
        }
        return DubboResponse.SUCCESS(homeworkPublishDao.findById(homeworkPublishId).get());
    }


}
