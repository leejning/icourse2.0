package com.online.icourse.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CourseDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private String courseName;
    private String courseType;

}
