package com.online.icourse.business.Dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName HomeworkPublishModify
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Data
public class HomeworkPublishModify {
    String id;
    private Date publishTime;
    private Date Deadline;
}
