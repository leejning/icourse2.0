package com.online.icourse.provider.service;

import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.Exercise;
import com.online.icourse.provider.bean.dto.ExerciseQueryDto;

/**
 * @ClassName ExerciseService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/3 0003
 * @Version V1.0
 **/
public interface ExerciseService {
    DubboResponse<QueryResult<Exercise>> queryExercise(ExerciseQueryDto exerciseQueryDto);

    DubboResponse<Void> deleteExercise(String exerciseId);

    DubboResponse<Void> modifyExercise(Exercise exercise);

    DubboResponse<Void> createExercise(Exercise exercise);

    DubboResponse<Exercise> getOneExercise(String exerciseId);
}
