package com.online.icourse.provider.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName Exercise
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@Data
@ToString
@Document(collection = "ic_exercise")
public class Exercise implements Serializable {

    private static final long serialVersionUID = 3714006659788780375L;
    @Id
    private String id;

    private Long courseId;
    private Long chapterId;
    private Date createTime;
    private Integer difficulty;
    /**
     * 题干
     */
    private String content;
    private String type;
    /**
     * 选项
     */
    private Map<String,String> selectItems;
    /**
     * 答案加解析
     */
    private Map<String,String> answer;
}
