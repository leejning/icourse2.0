package com.online.icourse.business.api;


import com.online.icourse.business.dto.request.StudentInfo;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.Student;
import com.online.icourse.provider.bean.dto.StudentQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "学生业务接口", description = "学生业务接口")
public interface StudentControllerApi {

    @ApiOperation("添加学生")
    public ResponseResult<String> addStudent(Student student);

    @ApiOperation("修改学生信息")
    public ResponseResult<String> modifyStudentInfo(Long stuid, StudentInfo studentInfo);

    @ApiOperation("获取学生列表")
    public QueryResponseResult<Student> getStudents(StudentQueryDto studentQueryDto);
}
