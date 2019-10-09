package com.online.icourse.provider.service;

import com.online.icourse.common.code.ExerciseCode;
import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.response.CommonCode;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.Exercise;
import com.online.icourse.provider.bean.dto.ExerciseQueryDto;
import com.online.icourse.provider.dao.ExerciseDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

/**
 * @ClassName ExerciseServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/3 0003
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseDao exerciseDao;

    @Override
    public DubboResponse<QueryResult<Exercise>> queryExercise(ExerciseQueryDto exerciseQueryDto) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(exerciseQueryDto);
        Query query = ExerciseQueryDto.getWhere(exerciseQueryDto);
        Page<Exercise> data = exerciseDao.findAll(query, pageAndSortObj.getPageable(), Exercise.class);
        QueryResult<Exercise> res = DataJpaPageUtils.setQueryResult(data, exerciseQueryDto);
        return DubboResponse.SUCCESS(res);
    }

    @Override
    public DubboResponse<Void> deleteExercise(String exerciseId) {
        exerciseDao.deleteById(exerciseId);
        return DubboResponse.SUCCESS(CommonCode.SUCCESS);
    }

    @Override
    public DubboResponse<Void> modifyExercise(Exercise exercise) {
        Exercise save = exerciseDao.save(exercise);
        return DubboResponse.SUCCESS(CommonCode.SUCCESS);
    }

    @Override
    public DubboResponse<Void> createExercise(Exercise exercise) {
        Exercise save = exerciseDao.save(exercise);
        return DubboResponse.SUCCESS(CommonCode.SUCCESS);
    }

    @Override
    public DubboResponse<Exercise> getOneExercise(String exerciseId) {
        Optional<Exercise> opt = exerciseDao.findById(exerciseId);
        if(!opt.isPresent()){
            DubboResponse.FAIL(ExerciseCode.NOT_EXSIST_EXERCISE_IN_THIS_ID);
        }
        return DubboResponse.SUCCESS(opt.get());
    }
}
