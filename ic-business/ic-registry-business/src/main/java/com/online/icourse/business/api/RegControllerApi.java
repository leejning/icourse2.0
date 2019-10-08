package com.online.icourse.business.api;

import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.IUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName RegControllerApi
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Api(value = "",description = "")
public interface RegControllerApi {
    @ApiOperation("")
    public ResponseResult<IUser> registry(IUser iUser);
}
