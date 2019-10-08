package com.online.icourse.provider.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Homework
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Data
@ToString
@Document(collection = "ic_homework")
public class Homework implements Serializable {
    private static final long serialVersionUID = -1036783360274299211L;
    @Id
    private String id;

    private Long courseId;

    private Date createTime;
    private String homeworkName;
    private Integer homeworkSumGrade;
    /**
     * 单选
     */
    private Integer singlechoiceNum;
    private Integer singlechoiceGrade;
    private List<String> singlechoiceIds;
    /**
     * 多选
     */
    private Integer multiplechoiceNum;
    private Integer multiplechoiceGrade;
    private List<String> multiplechoiceIds;
    /**
     * 判断
     */
    private Integer judgmentNum;
    private Integer judgmentGrade;
    private List<String> judgmentIds;
    /**
     * 填空
     */
    private Integer completionNum;
    private Integer completionGrade;
    private List<String> completionIds;
    /**
     * 简答
     */
    private Integer shortanswerNum;
    private Integer shortanswerGrade;
    private List<String> shortanswerIds;





}
