package com.online.icourse.provider.service;

import com.online.icourse.provider.dto.request.CourseInfo;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.Course;
import com.online.icourse.provider.bean.dto.CourseQueryDto;

public interface CourseService {
    Course save(Course course);

    Boolean modifyCourseInfo(CourseInfo courseInfo);

    Course deleteCourse(Long courseId);

    QueryResult<Course> findByJpa(CourseQueryDto courseQueryDto);
}
