package com.online.icourse.provider.service;

import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.response.CommonCode;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.Homework;
import com.online.icourse.provider.bean.dto.query.HomeworkQueryDto;
import com.online.icourse.provider.common.HomeworkCode;
import com.online.icourse.provider.dao.HomeworkDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;

/**
 * @ClassName HomeworkServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkDao homeworkDao;


    @Override
    public DubboResponse<Void> createHomework(Homework homework) {
        homework.setCreateTime(new Date());
        homeworkDao.save(homework);
        return DubboResponse.SUCCESS(CommonCode.SUCCESS);
    }

    @Override
    public DubboResponse<Void> deleteHomework(String homeworkId) {
        if(!homeworkDao.existsById(homeworkId)){
            return DubboResponse.FAIL(HomeworkCode.NOT_EXSIST_HOMEWORK_IN_THIS_ID);
        }
        homeworkDao.deleteById(homeworkId);
        return DubboResponse.SUCCESS(CommonCode.SUCCESS);
    }

    @Override
    public DubboResponse<QueryResult<Homework>> queryHomeworks(HomeworkQueryDto homeworkQueryDto) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(homeworkQueryDto);
        Query query = HomeworkQueryDto.getWhere(homeworkQueryDto);
        Page<Homework> data = homeworkDao.findAll(query, pageAndSortObj.getPageable(), Homework.class);
        QueryResult<Homework> res = DataJpaPageUtils.setQueryResult(data, homeworkQueryDto);
        return DubboResponse.SUCCESS(res);
    }

    @Override
    public DubboResponse<Homework> getOneHomework(String homeworkId) {
        if(!homeworkDao.existsById(homeworkId)){
            return DubboResponse.FAIL(HomeworkCode.NOT_EXSIST_HOMEWORK_IN_THIS_ID);
        }
        return DubboResponse.SUCCESS(homeworkDao.findById(homeworkId).get());
    }
}
