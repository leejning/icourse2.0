package com.online.icourse.business.controller;

import com.online.icourse.business.api.CourseControllerApi;
import com.online.icourse.business.dto.CourseDto;
import com.online.icourse.business.dto.CourseParam;
import com.online.icourse.business.dto.request.CourseAdd;
import com.online.icourse.business.dto.request.CourseInfo;
import com.online.icourse.common.code.CourseCode;
import com.online.icourse.common.model.response.CommonCode;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.Course;
import com.online.icourse.provider.bean.dto.CourseQueryDto;
import com.online.icourse.provider.service.CourseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CourseController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0
 **/
@RestController
@RequestMapping("course")
public class CourseController implements CourseControllerApi {

    @Reference(version = "2.0.0")
    private CourseService courseService;

    @Override
    @PostMapping("page/{pageNo}")
    public QueryResponseResult<CourseDto> getCoursesPage(@PathVariable Integer pageNo, @RequestBody CourseParam courseParam){
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("数学");
        courseDto.setCourseType("基础");
        QueryResult<CourseDto> queryResult = new QueryResult<>();
        List<CourseDto> list = new ArrayList<>();
        list.add(courseDto);
        queryResult.setList(list);
        return new QueryResponseResult<CourseDto>(CommonCode.SUCCESS,queryResult);
    }

    @PostMapping
    @Override
    public ResponseResult<Course> addCourse(@RequestBody CourseAdd courseAdd) {
        Course course = new Course();
        BeanUtils.copyProperties(courseAdd,course);
        course.setTeacherName("李哥");
        course.setTeacherId(1L);
        Course res = courseService.save(course);
        return ResponseResult.SUCCESS(res);
    }

    @PutMapping("{courseId}")
    @Override
    public ResponseResult<Course> modifyCourseInfo(@PathVariable Long courseId,@RequestBody CourseInfo courseInfo) {
        courseInfo.setCourseId(courseId);
        Boolean res = courseService.modifyCourseInfo(courseInfo);
        if(!res){
            return ResponseResult.FAIL(CourseCode.MODIFY_COURSE_INFO_FAIL);
        }
        return ResponseResult.SUCCESS("修改课程信息成功");
    }

    @GetMapping("/list")
    @Override
    public QueryResponseResult<Course> getCourseList(CourseQueryDto courseQueryDto) {
        QueryResult<Course> queryResult = courseService.findByJpa(courseQueryDto);
        return QueryResponseResult.SUCCESS(queryResult);
    }

    @DeleteMapping("{courseId}")
    @Override
    public ResponseResult<Course> removeCourse(@PathVariable Long courseId) {
        Course course = courseService.deleteCourse(courseId);
        return ResponseResult.SUCCESS(course);
    }


}
