package com.online.icourse.provider.dto.request;

import lombok.Data;

/**
 * @ClassName CourseInfo
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class CourseInfo {
    Long courseId;
    private String courseName;
    private String courseType;
    private String courseCover;
}
