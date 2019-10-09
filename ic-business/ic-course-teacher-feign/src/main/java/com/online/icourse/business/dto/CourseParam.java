package com.online.icourse.business.dto;

import lombok.Data;

/**
 * @ClassName CourseParam
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0
 **/
@Data
public class CourseParam {
    String courseName;
    Integer pageSize;
    String sortBy;
    Boolean desc;
}
