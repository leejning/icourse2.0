package com.online.icourse.business.controller;

import com.online.icourse.business.api.StudentControllerApi;
import com.online.icourse.business.dto.request.StudentInfo;
import com.online.icourse.common.code.StudentCode;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.Student;
import com.online.icourse.provider.bean.dto.StudentQueryDto;
import com.online.icourse.provider.service.StudentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName StudentController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@RestController
@RequestMapping("studnet")
public class StudentController implements StudentControllerApi {

    @Reference(version ="2.0.0")
    private StudentService studentService;

    @PostMapping
    @Override
    public ResponseResult<String> addStudent(@RequestBody Student student) {
        Student stu = studentService.save(student);
        if(stu.getId()==null){
            ResponseResult.FAIL(StudentCode.ADD_STUDENT_FAIL);
        }
        return ResponseResult.SUCCESS("添加学生成功");
    }

    @PutMapping("/{stuid}")
    @Override
    public ResponseResult<String> modifyStudentInfo(@PathVariable Long stuid, @RequestBody StudentInfo studentInfo) {
        Boolean res = studentService.modifyStudentInfo(stuid,studentInfo);
        if(!res){
            ResponseResult.FAIL(StudentCode.MODIFY_STUDENT_INFI_FAIL);
        }
        return ResponseResult.SUCCESS("修改学生信息成功");
    }

    @GetMapping("/list")
    @Override
    public QueryResponseResult<Student> getStudents(StudentQueryDto studentQueryDto) {
        QueryResult<Student> queryResult = studentService.findByjpa(studentQueryDto);
        return QueryResponseResult.SUCCESS(queryResult);
    }


}
