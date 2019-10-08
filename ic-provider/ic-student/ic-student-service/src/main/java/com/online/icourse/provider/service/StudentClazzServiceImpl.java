package com.online.icourse.provider.service;

import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.response.CommonCode;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.Student;
import com.online.icourse.provider.bean.StudentClazz;
import com.online.icourse.provider.bean.dto.ClazzStudentQueryDto;
import com.online.icourse.provider.bean.dto.StudentInfoDto;
import com.online.icourse.provider.common.StudentClazzCode;
import com.online.icourse.provider.dao.StudentClazzDao;
import com.online.icourse.provider.dao.StudentDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentClazzServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class StudentClazzServiceImpl implements StudentClazzService {
    @Autowired
    private StudentClazzDao studentClazzDao;
    @Autowired
    private StudentDao studentDao;

    /**
     * 添加学生到课堂
     * @param studentId
     * @param clazzId
     * @return
     */
    @Override
    public DubboResponse<Void> addStudent2Clazz(Long studentId, Long clazzId) {
        int res = studentClazzDao.checkClazzHasStudent(studentId, clazzId);
        if (res > 0) {
            return DubboResponse.FAIL(StudentClazzCode.STUDENT_IS_IN_THIS_CLASS);
        }
        StudentClazz studentClazz = new StudentClazz();
        studentClazz.setStudentId(studentId);
        studentClazz.setClazzId(clazzId);
        studentClazz.setCourseId(1L);
        studentClazzDao.save(studentClazz);
        return DubboResponse.SUCCESS(CommonCode.SUCCESS);
    }

    @Override
    public DubboResponse<Void> addManyStudents2Clazz(List<Long> studentIds, Long clazzId) {

        return null;
    }

    /**
     * 教师查询课堂学生
     * @param queryDto
     * @return
     */
    @Override
    public DubboResponse<QueryResult<StudentInfoDto>> queryClazzStudent(ClazzStudentQueryDto queryDto) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(queryDto);
        Page<Student> queryData = studentDao.queryClazzStudent(queryDto, pageAndSortObj.getPageable());
        List<StudentInfoDto> data = new ArrayList<>();
        queryData.forEach(stu -> {
            StudentInfoDto studentInfo = new StudentInfoDto();
            BeanUtils.copyProperties(stu, studentInfo);
            data.add(studentInfo);
        });
        QueryResult<StudentInfoDto> resData = DataJpaPageUtils.setQueryResult(data, queryData.getTotalElements(), queryDto);
        return DubboResponse.SUCCESS(resData);
    }

    /**
     * 根据课堂id获取课堂学生的id
     * @param clazzId
     * @return
     */
    @Override
    public DubboResponse<Map<Long,List<Long>>> getStudentIdByClazzIds(List<Long> clazzId) {
        Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
        clazzId.forEach(cid -> {
            List<Long> sids = studentClazzDao.findStudentIdByclazzId(cid);
            map.put(cid, sids);
        });
        return DubboResponse.SUCCESS(map);
    }
}
