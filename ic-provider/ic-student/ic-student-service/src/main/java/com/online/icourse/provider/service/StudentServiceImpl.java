package com.online.icourse.provider.service;

import com.online.icourse.business.dto.request.StudentInfo;
import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.Student;
import com.online.icourse.provider.bean.dto.StudentQueryDto;
import com.online.icourse.provider.dao.StudentDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @ClassName StudentService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;


    @Override
    public Student save(Student student) {
        student.setId(null);
        return studentDao.save(student);
    }

    @Override
    public Student getUserByUserId(Long userId) {
        Optional<Student> opt = studentDao.findByUserId(userId);
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    @Override
    public Boolean modifyStudentInfo(Long stuid, StudentInfo studentInfo) {
        Optional<Student> opt = studentDao.findById(stuid);
        if(opt.isPresent()){
            Student student = opt.get();
            BeanUtils.copyProperties(studentInfo,student);
            studentDao.save(student);
            return true;
        }
        return false;
    }

    @Override
    public QueryResult<Student> findByjpa(StudentQueryDto studentQueryDto) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(studentQueryDto);
        Page<Student> data = studentDao.findAll(StudentQueryDto.getWhere(studentQueryDto), pageAndSortObj.getPageable());
        QueryResult<Student>  queryResult = DataJpaPageUtils.setQueryResult(data,studentQueryDto);
        return queryResult;
    }
}
