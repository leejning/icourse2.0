package com.online.icourse.provider.dao;

import com.online.icourse.provider.bean.AnswerRecord;
import com.online.icourse.provider.support.BaseDao;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @ClassName AnswerRecordDao
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
public interface AnswerRecordDao extends BaseDao<AnswerRecord,String> {
    @Query(value="{'homeworkPublishId':?0,'studentId':?1}",
            fields = "{'studentId':1,'clazzId':1,'homeworkPublishId':1,'publishTime':1,'Deadline':1,'finishTime':1}")
    List<AnswerRecord> findOneByHomeworkPublishIdAndStudentId(String hpid, Long studentId);

    @Query(value="{'studentId':?1,'homeworkPublishId':?0}",
            fields = "{'studentId':1,'clazzId':1,'homeworkPublishId':1,'publishTime':1,'Deadline':1,'finishTime':1}")
    List<AnswerRecord> findByHpId(String hpid,Long stidentid);
}
