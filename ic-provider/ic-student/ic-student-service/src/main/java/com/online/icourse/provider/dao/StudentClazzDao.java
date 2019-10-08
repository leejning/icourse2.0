package com.online.icourse.provider.dao;

import com.online.icourse.provider.bean.StudentClazz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentClazzDao extends CrudRepository<StudentClazz, Long> {
    @Query("select count(studentId) from StudentClazz where studentId = ?1 and clazzId = ?2")
    int checkClazzHasStudent(Long studentId, Long clazzId);

    @Query("select studentId from StudentClazz where clazzId = ?1")
    List<Long> findStudentIdByclazzId(Long cid);
}
