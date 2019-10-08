package com.online.icourse.business.controller;

import com.online.icourse.business.api.ClazzStudentControllerApi;
import com.online.icourse.common.exception.ExceptionCast;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.dto.ClazzStudentQueryDto;
import com.online.icourse.provider.bean.dto.StudentInfoDto;
import com.online.icourse.provider.service.StudentClazzService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ClazzStudentController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@RestController
@RequestMapping("clazz")
public class ClazzStudentController implements ClazzStudentControllerApi {
    @Reference(version = "2.0.0")
    public StudentClazzService studentClazzService;


    @PostMapping("student/{clazzId}")
    @Override
    public ResponseResult<Void> addStudent2Clazz(Long studentId, @PathVariable Long clazzId) {
        DubboResponse<Void> res = studentClazzService.addStudent2Clazz(studentId, clazzId);
        if(!res.isSuccess()){
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS("添加学生成功");
    }

    @PostMapping("students/{clazzId}")
    @Override
    public ResponseResult<String> addManyStudents2Clazz(List<Long> studentIds, @PathVariable Long clazzId) {
        DubboResponse<Void> res = studentClazzService.addManyStudents2Clazz(studentIds, clazzId);
        if(!res.isSuccess()){
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS("批量添加学生成功");

    }

    @GetMapping("students")
    @Override
    public QueryResponseResult<StudentInfoDto> queryClazzStudent(ClazzStudentQueryDto queryDto) {
        DubboResponse<QueryResult<StudentInfoDto>> res = studentClazzService.queryClazzStudent(queryDto);
        if(!res.isSuccess()){
            ExceptionCast.cast(res.getResultCode());
        }
        return QueryResponseResult.SUCCESS(res.getData());
    }
}
