package com.online.icourse.business.api;

import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.dto.ClazzStudentQueryDto;
import com.online.icourse.provider.bean.dto.StudentInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "课堂学生管理接口",description = "课堂学生管理接口")
public interface ClazzStudentControllerApi {

    @ApiOperation("添加学生到课堂")
    public ResponseResult<Void> addStudent2Clazz(Long studentId,Long clazzId);

    @ApiOperation("批量添加学生到课堂")
    public ResponseResult<String> addManyStudents2Clazz(List<Long> studentIds,Long clazzId);

    @ApiOperation("获取课堂学生列表")
    public QueryResponseResult<StudentInfoDto> queryClazzStudent(ClazzStudentQueryDto queryDto);

}
