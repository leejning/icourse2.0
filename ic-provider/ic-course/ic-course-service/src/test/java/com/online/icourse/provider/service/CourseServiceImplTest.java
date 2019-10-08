package com.online.icourse.provider.service;

import com.online.icourse.provider.CourseServiceApplication;
import com.online.icourse.provider.bean.Course;
import com.online.icourse.provider.dao.CourseDao;
import com.online.icourse.provider.jpa.NbQueryBuilder;
import com.online.icourse.provider.jpa.simple.SelectorBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {CourseServiceApplication.class})
@RunWith(SpringRunner.class)
public class CourseServiceImplTest {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    EntityManager entityManager;

    @Test
    public void findByJpa() {
        /*CourseQueryDto courseQueryDto = new CourseQueryDto();
        courseQueryDto.setCourseName("java");
        courseQueryDto.setPageNo(1);
        courseQueryDto.setPageSize(1);
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(courseQueryDto);
        Page<Course> data = courseDao.findAll(CourseQueryDto.getWhere(courseQueryDto), pageAndSortObj.getPageable());
        System.out.println(data.getContent());*/

        NbQueryBuilder<Course> queryBuilder = new NbQueryBuilder<>();

        SelectorBuilder selectorBuilder = new SelectorBuilder();
        selectorBuilder.append("courseName","teacherName");
        queryBuilder.setSelectorBuilder(selectorBuilder);
        List<Tuple> result = queryBuilder.findResult(entityManager, Course.class);
        result.forEach(item->{
            Object[] objects = item.toArray();
            for (int i = 0; i < objects.length; i++) {
                System.out.print(objects[i]);
            }
        });
    }

    @Test
    public void saveCourse(){
        List<String> courseName = Arrays.asList("网页设计与开发","php编程基础","面向对象程序设计c++",
                "Oracle数据库教程","java基础入门","jsp程序设计","计算机组成原理","计算机操作系统","计算机网络",
                "数据库系统原理与设计");

        courseName.forEach(name->{
            Course course = new Course();
            course.setCourseName(name);
            course.setCourseType("计算机");
            course.setTeacherName("李哥");
            course.setTeacherId(1L);
            courseDao.save(course);
        });


    }
}
