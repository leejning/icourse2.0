package com.online.icourse.provider.service;


import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.Student;
import com.online.icourse.provider.bean.StudentClazz;
import com.online.icourse.provider.bean.dto.ClazzStudentQueryDto;
import com.online.icourse.provider.dao.StudentClazzDao;
import com.online.icourse.provider.dao.StudentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceImplTest {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentClazzDao studentClazzDao;
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    @Test
//    public void testMQ(){
//        amqpTemplate.convertAndSend("homework.publish","aksjfhjkleqwnlasknc");
//    }

    @Test
    public void addStudent() {
        long uid = 8L;
        for (int i = 1; i < 40; i++) {
            for (int j = 1; j <= 3; j++) {
                Student student = new Student();
                student.setStudentNo(2041404100L + j * 100 + i + 9);
                student.setStudentName("测试学生" + i++);
                student.setUserId(uid++);
                student.setClassName("软件" + j + "班");
                student.setGrade(4);
                studentDao.save(student);
            }
        }
    }

    @Test
    public void addStuToclass() {

        long cid = 1;
        for (int k = 1; k <= 12; k++) {
            for (int j = 1; j < 4; j++) {
                for (long i = 37 + j; i < 68; i += 3) {
                    StudentClazz studentClazz = new StudentClazz();
                    studentClazz.setStudentId(i);
                    studentClazz.setClazzId(cid);
                    studentClazz.setCourseId(cid/3+1);
                    studentClazzDao.save(studentClazz);
                }
                cid++;
            }
        }


    }

    @Test
    public void query() {
        ClazzStudentQueryDto queryDto = new ClazzStudentQueryDto();
        queryDto.setClazzId(1L);
        queryDto.setPageNo(1);
        queryDto.setPageSize(3);
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(queryDto);
        Page<Student> students = studentDao.queryClazzStudent(queryDto, pageAndSortObj.getPageable());
//        List<Map<String, Object>> maps = studentDao.queryClazzStudent(queryDto);
        System.out.println(students.getContent());
    }

}
