package com.online.icourse.provider.service;

import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.Course;
import com.online.icourse.provider.bean.dto.CourseQueryDto;
import com.online.icourse.provider.dao.CourseDao;
import com.online.icourse.provider.dto.request.CourseInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @ClassName CourseService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    EntityManager entityManager;


    @Override
    public Course save(Course course) {
        return courseDao.save(course);
    }

    @Override
    public Boolean modifyCourseInfo(CourseInfo courseInfo) {
        Optional<Course> opt = courseDao.findById(courseInfo.getCourseId());
        if (opt.isPresent()) {
            Course course = opt.get();
            BeanUtils.copyProperties(courseInfo, course);
            courseDao.save(course);
            return true;
        }
        return false;
    }

    @Override
    public Course deleteCourse(Long courseId) {
        Optional<Course> opt = courseDao.findById(courseId);
        if (opt.isPresent()) {
            Course course = opt.get();
            courseDao.deleteById(courseId);
            return course;
        }
        return null;
    }

    @Override
    public QueryResult<Course> findByJpa(CourseQueryDto courseQueryDto) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(courseQueryDto);
        Page<Course> data = courseDao.findAll(CourseQueryDto.getWhere(courseQueryDto), pageAndSortObj.getPageable());
        return DataJpaPageUtils.setQueryResult(data,courseQueryDto);
    }
}
