package com.online.icourse.business.api;


import com.online.icourse.business.dto.ClazzBaseAdd;
import com.online.icourse.business.dto.ClazzBaseInfo;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.ClazzBase;
import com.online.icourse.provider.bean.dto.ClazzBaseQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "课堂基本信息业务接口",description = "课堂基本信息业务接口")
public interface ClazzBaseControllerApi {
    @ApiOperation("创建新课堂")
    public ResponseResult<ClazzBase> createClazz(ClazzBaseAdd clazzBaseAdd);

    @ApiOperation("查询课堂")
    public QueryResponseResult<ClazzBase> getClazzList(ClazzBaseQueryDto clazzQueryDto);

    @ApiOperation("修改课堂信息")
    public ResponseResult<ClazzBase> modifyClazzInfo(Long clazzId, ClazzBaseInfo clazzBaseInfo);

    @ApiOperation("删除课堂")
    public ResponseResult removeCalzz(Long clazzId);

}
