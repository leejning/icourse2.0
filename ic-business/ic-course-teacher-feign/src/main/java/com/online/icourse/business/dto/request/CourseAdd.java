package com.online.icourse.business.dto.request;

import lombok.Data;

/**
 * @ClassName CourseAdd
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class CourseAdd {
    private String courseName;
    private String courseType;
    private String courseCover;
}
