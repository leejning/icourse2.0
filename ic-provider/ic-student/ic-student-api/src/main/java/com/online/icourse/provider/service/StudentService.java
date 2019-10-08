package com.online.icourse.provider.service;

import com.online.icourse.business.dto.request.StudentInfo;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.Student;
import com.online.icourse.provider.bean.dto.StudentQueryDto;

public interface StudentService {

    public Student save(Student student);

    public Student getUserByUserId(Long userId);

    public Boolean modifyStudentInfo(Long stuid, StudentInfo studentInfo);

    QueryResult<Student> findByjpa(StudentQueryDto studentQueryDto);
}
