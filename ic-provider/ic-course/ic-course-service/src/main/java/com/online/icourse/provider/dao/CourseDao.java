package com.online.icourse.provider.dao;

import com.online.icourse.provider.bean.Course;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseDao extends PagingAndSortingRepository<Course,Long>, JpaSpecificationExecutor<Course> {
}
