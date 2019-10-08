package com.online.icourse.provider.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HomeworkPublish
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Data
@ToString
@Document(collection = "ic_homework_publish")
public class HomeworkPublish {
    @Id
    private String id;

    private String publishName;
    private String homeworkId;
    private List<Long> clazzIds;
    private List<String> clazzName;

    private Date publishTime;
    private Date Deadline;

}
