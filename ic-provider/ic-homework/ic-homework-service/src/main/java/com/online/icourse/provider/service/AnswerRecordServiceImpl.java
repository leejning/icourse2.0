package com.online.icourse.provider.service;

import com.mongodb.BasicDBObject;
import com.online.icourse.business.Dto.PublishHwStuQuery;
import com.online.icourse.common.exception.ExceptionCast;
import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.response.CommonCode;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.AnswerRecord;
import com.online.icourse.provider.bean.Homework;
import com.online.icourse.provider.bean.HomeworkPublish;
import com.online.icourse.provider.bean.dto.request.AnswerRecordModify;
import com.online.icourse.provider.bean.dto.query.AnswerRecordQueryDto;
import com.online.icourse.provider.common.AnswerRecordCode;
import com.online.icourse.provider.common.HomeworkCode;
import com.online.icourse.provider.dao.AnswerRecordDao;
import com.online.icourse.provider.dao.HomeworkDao;
import com.online.icourse.provider.dao.HomeworkPublishDao;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName AnswerRecordServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class AnswerRecordServiceImpl implements AnswerRecordService {
    @Autowired
    private AnswerRecordDao answerRecordDao;

    @Reference(version = "2.0.0")
    private StudentClazzService studentClazzService;
    @Autowired
    private HomeworkPublishDao homeworkPublishDao;
    @Autowired
    private HomeworkDao homeworkDao;

    /**
     * 发布作业后，为相关的每个学生创建作业记录
     *
     * @param hpId
     */
    @Override
    public void afterPublishHomeworkCreateRecord(String hpId) {
        Optional<HomeworkPublish> optp = homeworkPublishDao.findById(hpId);
        if (!optp.isPresent()) {
            ExceptionCast.cast(HomeworkCode.NOT_EXSIST_PUBLISH_HOMEWORK_IN_THIS_ID);
        }
        HomeworkPublish homeworkPublish = optp.get();
        Optional<Homework> opth = homeworkDao.findById(homeworkPublish.getHomeworkId());
        if (!opth.isPresent()) {
            ExceptionCast.cast(HomeworkCode.NOT_EXSIST_PUBLISH_HOMEWORK_IN_THIS_ID);
        }
        Homework homework = opth.get();
        List<String> completionIds = homework.getCompletionIds();
        Map<String, String> completionRec = null;
        if (completionIds != null) {
            completionRec = completionIds.stream().collect(Collectors.toMap(id -> id, id -> ""));
        }
        List<String> singlechoiceIds = homework.getSinglechoiceIds();
        Map<String, String> singlechoiceRec = null;
        if (singlechoiceIds != null) {
            singlechoiceRec = singlechoiceIds.stream().collect(Collectors.toMap(id -> id, id -> ""));
        }
        List<String> multiplechoiceIds = homework.getMultiplechoiceIds();
        Map<String, String> multiplechoiceRec = null;
        if (multiplechoiceIds != null) {
            multiplechoiceRec = multiplechoiceIds.stream().collect(Collectors.toMap(id -> id, id -> ""));
        }
        List<String> judgmentIds = homework.getJudgmentIds();
        Map<String, String> judgmentRec=null;
        if (judgmentIds != null) {
            judgmentRec = judgmentIds.stream().collect(Collectors.toMap(id -> id, id -> ""));
        }
        List<String> shortanswerIds = homework.getShortanswerIds();
        Map<String, String> shortanswerRec = null;
        if (shortanswerIds != null) {
            shortanswerRec = shortanswerIds.stream().collect(Collectors.toMap(id -> id, id -> ""));
        }
        List<Long> clazzIds = homeworkPublish.getClazzIds();
        DubboResponse<Map<Long, List<Long>>> res = studentClazzService.getStudentIdByClazzIds(clazzIds);
        if (!res.isSuccess()) {
            ExceptionCast.cast(CommonCode.FAIL);
        }

        List<AnswerRecord> list = new ArrayList<>();
        Map<String, String> finalSinglechoiceRec = singlechoiceRec;
        Map<String, String> finalMultiplechoiceRec = multiplechoiceRec;
        Map<String, String> finalCompletionRec = completionRec;
        Map<String, String> finalJudgmentRec = judgmentRec;
        Map<String, String> finalShortanswerRec = shortanswerRec;
        res.getData().entrySet().stream().forEach(clazz -> {
            List<Long> stu = clazz.getValue();
            stu.forEach(sid -> {
                AnswerRecord answerRecord = new AnswerRecord();
                answerRecord.setHomeworkPublishId(hpId);
                answerRecord.setHomeworkPublishName(homeworkPublish.getPublishName());
                answerRecord.setPublishTime(homeworkPublish.getPublishTime());
                answerRecord.setDeadline(homeworkPublish.getDeadline());
                answerRecord.setStudentId(sid);
                answerRecord.setClazzId(clazz.getKey());
                answerRecord.setSinglechoiceRec(finalSinglechoiceRec);
                answerRecord.setMultiplechoiceRec(finalMultiplechoiceRec);
                answerRecord.setCompletionRec(finalCompletionRec);
                answerRecord.setJudgmentRec(finalJudgmentRec);
                answerRecord.setShortanswerRec(finalShortanswerRec);
                list.add(answerRecord);
            });
        });
        answerRecordDao.saveAll(list);
    }

    /**
     * 教师获取发布作业相关的学生列表
     * @param publishHwStuQuery
     * @return
     */
    @Override
    public DubboResponse<QueryResult<AnswerRecord>> getOnePublishHomeworkStudentList(PublishHwStuQuery publishHwStuQuery) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(publishHwStuQuery);
        AnswerRecordQueryDto queryDto = new AnswerRecordQueryDto();
        BeanUtils.copyProperties(publishHwStuQuery,queryDto);
        Query query = AnswerRecordQueryDto.getWhere(queryDto,AnswerRecordQueryDto.viewList());
        Page<AnswerRecord> data = answerRecordDao.findAll(query, pageAndSortObj.getPageable(), AnswerRecord.class);

        QueryResult<AnswerRecord> queryResult = DataJpaPageUtils.setQueryResult(data, publishHwStuQuery);
        return DubboResponse.SUCCESS(queryResult);
    }

    /**
     * 教师查看某个学生的作业作答详情
     * @param arId
     * @return
     */
    @Override
    public DubboResponse<AnswerRecord> findById(String arId) {
        Optional<AnswerRecord> opt = answerRecordDao.findById(arId);
        if(!opt.isPresent()){
            DubboResponse.FAIL(AnswerRecordCode.NOT_EXSIST_ANSWER_RECORD_IN_THIS_ID);
        }
        return DubboResponse.SUCCESS(opt.get());
    }
    /**
     * 学生获取作业列表
     * @param queryDto
     * @return
     */
    @Override
    public DubboResponse<QueryResult<AnswerRecord>> getStudentHomeworkList(AnswerRecordQueryDto queryDto) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(queryDto);
        Query query = AnswerRecordQueryDto.getWhere(queryDto);
        Page<AnswerRecord> data = answerRecordDao.findAll(query, pageAndSortObj.getPageable(), AnswerRecord.class);
        QueryResult<AnswerRecord> res = DataJpaPageUtils.setQueryResult(data, queryDto);
        return DubboResponse.SUCCESS(res);
    }

    /**
     * 学生获取作业作答记录
     *
     * @param hpid
     * @param studentId
     * @return
     */
    @Override
    public DubboResponse<AnswerRecord> getHomeworkAnswerRecord(String hpid, Long studentId) {
        AnswerRecordQueryDto queryDto = new AnswerRecordQueryDto();
        queryDto.setHpId(hpid);
        queryDto.setStudentId(studentId);
        Query query = AnswerRecordQueryDto.getWhere(queryDto);
        Optional<AnswerRecord> opt = answerRecordDao.findOne(query, AnswerRecord.class);
        if (!opt.isPresent()) {
            return DubboResponse.FAIL(AnswerRecordCode.NOT_EXSIST_ANSWER_RECORD_IN_THIS_ID);
        }
        return DubboResponse.SUCCESS(opt.get());
    }

    /**
     * 每隔一段时间自动保存学生作答记录
     *
     * @param answerRecordModify
     * @return
     */
    @Override
    public DubboResponse<Void> saveAnswerRecord(AnswerRecordModify answerRecordModify) {
        AnswerRecordQueryDto queryDto = new AnswerRecordQueryDto();
        BeanUtils.copyProperties(answerRecordModify, queryDto);
        BasicDBObject fields = new BasicDBObject();
        //过滤要修改的答案记录
        if (answerRecordModify.getSinglechoiceRec() != null) {
            fields.append("singlechoice", true);
        }
        if (answerRecordModify.getMultiplechoiceRec() != null) {
            fields.append("multiplechoice", true);
        }
        if (answerRecordModify.getJudgmentRec() != null) {
            fields.append("judgment", true);
        }
        if (answerRecordModify.getCompletionRec() != null) {
            fields.append("completion", true);
        }
        if (answerRecordModify.getShortanswerRec() != null) {
            fields.append("shortanswer", true);
        }
        //构建查询
        Query query = AnswerRecordQueryDto.getWhere(queryDto);
        //获取记录
        Optional<AnswerRecord> opt = answerRecordDao.findOne(query, AnswerRecord.class);
        if (!opt.isPresent()) {
            return DubboResponse.FAIL(AnswerRecordCode.STUDENT_HAS_NOT_THIS_HOMEWORK);
        }
        AnswerRecord answerRecord = opt.get();

        //修改答案记录
        if (answerRecordModify.getSinglechoiceRec() != null) {
            Map<String, String> map = answerRecord.getSinglechoiceRec();
            Set<Map.Entry<String, String>> entries = answerRecordModify.getSinglechoiceRec().entrySet();
            entries.stream().forEach(item -> map.put(item.getKey(), item.getValue()));
        }
        if (answerRecordModify.getMultiplechoiceRec() != null) {
            Map<String, String> map = answerRecord.getMultiplechoiceRec();
            Set<Map.Entry<String, String>> entries = answerRecordModify.getMultiplechoiceRec().entrySet();
            entries.stream().forEach(item -> map.put(item.getKey(), item.getValue()));
        }
        if (answerRecordModify.getJudgmentRec() != null) {
            Map<String, String> map = answerRecord.getJudgmentRec();
            Set<Map.Entry<String, String>> entries = answerRecordModify.getJudgmentRec().entrySet();
            entries.stream().forEach(item -> map.put(item.getKey(), item.getValue()));
        }
        if (answerRecordModify.getCompletionRec() != null) {
            Map<String, String> map = answerRecord.getCompletionRec();
            Set<Map.Entry<String, String>> entries = answerRecordModify.getCompletionRec().entrySet();
            entries.stream().forEach(item -> map.put(item.getKey(), item.getValue()));
        }
        if (answerRecordModify.getShortanswerRec() != null) {
            Map<String, String> map = answerRecord.getShortanswerRec();
            Set<Map.Entry<String, String>> entries = answerRecordModify.getShortanswerRec().entrySet();
            entries.stream().forEach(item -> map.put(item.getKey(), item.getValue()));
        }
        //重新保存
        answerRecordDao.save(answerRecord);
        return DubboResponse.SUCCESS(CommonCode.SUCCESS);
    }


}
