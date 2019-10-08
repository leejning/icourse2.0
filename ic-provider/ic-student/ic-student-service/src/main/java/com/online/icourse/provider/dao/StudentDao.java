package com.online.icourse.provider.dao;

import com.online.icourse.provider.bean.Student;
import com.online.icourse.provider.bean.dto.ClazzStudentQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName StudentDao
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
public interface StudentDao extends PagingAndSortingRepository<Student,Long>, JpaSpecificationExecutor<Student> {
    Optional<Student> findByUserId(Long userId);

   /*
   * 获取班级学生
   */
    @Query(value =
            "select new Student(s.id,s.studentNo,s.studentName,s.className,s.grade) " +
                    "from Student s where s.id in " +
                    "(select c.studentId from StudentClazz c where c.clazzId = :#{#queryDto.clazzId})")
    Page<Student> queryClazzStudent(@Param("queryDto")ClazzStudentQueryDto queryDto, Pageable pageable);


    @Query(value =
            "select s.id,s.student_no,s.student_name,s.class_name,s.grade from ic_student s where s.id in (select c.student_id from ic_clazz_student c where c.clazz_id = 1)",nativeQuery = true)
    List<Map<String,Object>> queryClazzStudent(ClazzStudentQueryDto queryDto);
}
