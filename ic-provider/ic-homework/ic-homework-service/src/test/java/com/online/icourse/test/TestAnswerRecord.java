package com.online.icourse.test;

import com.online.icourse.provider.HomeworkApplication;
import com.online.icourse.provider.bean.AnswerRecord;
import com.online.icourse.provider.bean.HomeworkPublish;
import com.online.icourse.provider.bean.dto.query.AnswerRecordQueryDto;
import com.online.icourse.provider.dao.AnswerRecordDao;
import com.online.icourse.provider.dao.HomeworkPublishDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @ClassName TestAnswerRecord
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/6 0006
 * @Version V1.0
 **/
@SpringBootTest(classes = {HomeworkApplication.class})
@RunWith(SpringRunner.class)
public class TestAnswerRecord {
    @Autowired
    private AnswerRecordDao answerRecordDao;
    @Autowired
    private HomeworkPublishDao homeworkPublishDao;
    @Test
    public void find(){
        HomeworkPublish homeworkPublish = homeworkPublishDao.findById("5d98aeb868e6d92a3cac5c1d").get();
        System.out.println(homeworkPublish);
    }

    @Test
    public void getAnswerRecord(){
        AnswerRecordQueryDto queryDto = new AnswerRecordQueryDto();
        queryDto.setHpId("5d9966a668e6d931c000a6be");
        queryDto.setStudentId(38L);
        Query query = queryDto.getWhere(queryDto);
        Optional<AnswerRecord> opt = answerRecordDao.findOne(query, AnswerRecord.class);
        System.out.println(opt.get());
    }
}
