package com.online.icourse.business.controller;

import com.online.icourse.business.api.ExerciseControllerApi;
import com.online.icourse.common.exception.ExceptionCast;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.Exercise;
import com.online.icourse.provider.bean.dto.ExerciseQueryDto;
import com.online.icourse.provider.service.ExerciseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ExerciseControllrt
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/3 0003
 * @Version V1.0
 **/
@RestController
@RequestMapping("exercise")
public class ExerciseControllrt implements ExerciseControllerApi {
    @Reference(version = "2.0.0")
    public ExerciseService exerciseService;

    @PostMapping
    @Override
    public ResponseResult<Void> createExercise(@RequestBody Exercise exercise) {
        DubboResponse res = exerciseService.createExercise(exercise);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS("添加习题成功");
    }

    @PutMapping("{exerciseId}")
    @Override
    public ResponseResult<Void> modifyExercise(Exercise exercise) {
        DubboResponse res = exerciseService.modifyExercise(exercise);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS("修改习题成功");
    }

    @DeleteMapping("{exerciseId}")
    @Override
    public ResponseResult<Exercise> deleteExercise(String exerciseId) {
        DubboResponse res = exerciseService.deleteExercise(exerciseId);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS("删除习题成功");
    }

    @GetMapping("/page")
    @Override
    public QueryResponseResult<Exercise> queryExercise(ExerciseQueryDto exerciseQueryDto) {
        DubboResponse<QueryResult<Exercise>> res = exerciseService.queryExercise(exerciseQueryDto);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return QueryResponseResult.SUCCESS(res.getData());
    }

    @GetMapping("{exerciseId}")
    @Override
    public ResponseResult<Exercise> getOneExercise(@PathVariable String exerciseId) {
        DubboResponse<Exercise> res = exerciseService.getOneExercise(exerciseId);
        if (!res.isSuccess()) {
            ExceptionCast.cast(res.getResultCode());
        }
        return ResponseResult.SUCCESS(res.getData());
    }
}
