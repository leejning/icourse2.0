package com.online.icourse.provider.service;

import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.dto.ClazzStudentQueryDto;
import com.online.icourse.provider.bean.dto.StudentInfoDto;

import java.util.List;
import java.util.Map;

public interface StudentClazzService {
    DubboResponse<Void> addStudent2Clazz(Long studentId, Long clazzId);

    DubboResponse<Void> addManyStudents2Clazz(List<Long> studentIds, Long clazzId);

    DubboResponse<QueryResult<StudentInfoDto>> queryClazzStudent(ClazzStudentQueryDto queryDto);

    DubboResponse<Map<Long,List<Long>>> getStudentIdByClazzIds(List<Long> clazzId);
}
