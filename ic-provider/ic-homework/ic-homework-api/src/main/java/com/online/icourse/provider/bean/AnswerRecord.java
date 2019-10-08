package com.online.icourse.provider.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName AnswerRecord
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@Data
@ToString
@Document(collection = "ic_answer_record")
public class AnswerRecord {
    @Id
    private String id;
    private Long studentId;
    private String studentName;
    private Long clazzId;
    private String homeworkPublishId;
    private String homeworkPublishName;
    /**
     * 发布作业名
     */
    private Date publishTime;
    private Date Deadline;
    private Data finishTime;
    /**
     * 单选
     */
    private Map<String,String> singlechoiceRec;
    /**
     * 多选
     */
    private Map<String,String> multiplechoiceRec;
    /**
     * 判断
     */
    private Map<String,String> judgmentRec;
    /**
     * 填空
     */
    private Map<String,String> completionRec;
    /**
     * 简答
     */
    private Map<String,String> shortanswerRec;

}
