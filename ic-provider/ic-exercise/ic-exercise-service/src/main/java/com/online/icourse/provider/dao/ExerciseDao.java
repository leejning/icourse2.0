package com.online.icourse.provider.dao;

import com.online.icourse.provider.bean.Exercise;
import com.online.icourse.provider.support.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

/**
 * @ClassName ExerciseDao
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/3 0003
 * @Version V1.0
 **/
public interface ExerciseDao extends BaseDao<Exercise,String> {
    @Query(value="{'courseId':?0}",
            fields="{'courseId':1,'createTime':1,'content':1,'selectItem':1}")
    public Page<Exercise> findByCourseId(Long courseId, Pageable pageable);

    @Query(value="{'content':?0}",
            fields="{'courseId':1,'createTime':1,'content':1,'selectItem':1}")
    public Page<Exercise> findByContentLike(String content, Pageable pageable);
}
