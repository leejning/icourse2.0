package com.online.icourse.business.api;

import com.online.icourse.provider.dto.CourseDto;
import com.online.icourse.provider.dto.CourseParam;
import com.online.icourse.provider.dto.request.CourseAdd;
import com.online.icourse.provider.dto.request.CourseInfo;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.Course;
import com.online.icourse.provider.bean.dto.CourseQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@Api(value = "课程业务接口",description = "课程服务接口")
public interface CourseControllerApi {

    @ApiOperation("获取课程列表")
    public QueryResponseResult<CourseDto> getCoursesPage( Integer pageNo, CourseParam courseParam);

    @ApiOperation("添加新课程")
    public ResponseResult<Course> addCourse(CourseAdd course);

    @ApiOperation("修改课程信息")
    public ResponseResult<Course> modifyCourseInfo(Long courseId,CourseInfo courseInfo);

    @ApiOperation("分页获取课程列表")
    public QueryResponseResult<Course> getCourseList(CourseQueryDto courseQueryDto);

    @ApiOperation("删除课程")
    public ResponseResult<Course> removeCourse(Long courseId);
}
