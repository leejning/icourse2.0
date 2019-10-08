package com.online.icourse.business.api;

import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.Exercise;
import com.online.icourse.provider.bean.dto.ExerciseQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName ExerciseControllerApi
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/3 0003
 * @Version V1.0
 **/
@Api(value = "教师课程习题管理", description = "教师课程习题管理接口")
public interface ExerciseControllerApi {
    @ApiOperation("添加习题")
    public ResponseResult<Void> createExercise(Exercise exercise);

    @ApiOperation("修改习题")
    public ResponseResult<Void> modifyExercise(Exercise exercise);

    @ApiOperation("删除习题")
    public ResponseResult<Exercise> deleteExercise(String id);

    @ApiOperation("分页查询习题")
    public QueryResponseResult<Exercise> queryExercise(ExerciseQueryDto exerciseQueryDto);

    @ApiOperation("获取某个习题")
    public ResponseResult<Exercise> getOneExercise(String exerciseId);
}
