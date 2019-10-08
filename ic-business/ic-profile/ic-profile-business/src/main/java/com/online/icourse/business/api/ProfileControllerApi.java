package com.online.icourse.business.api;

import com.online.icourse.business.dto.UserIcon;
import com.online.icourse.business.dto.UserInfo;
import com.online.icourse.business.dto.UserModify;
import com.online.icourse.business.dto.UserPassword;
import com.online.icourse.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/** 
 * @ClassName ProfileControllerApi
 * @Description: TODO 
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0.0
**/
@Api(value="用户信息业务接口",description = "用户信息管理")
public interface ProfileControllerApi {

    @ApiOperation("获取基本信息")
    public ResponseResult<UserInfo> getUserInfo(HttpServletRequest req);

    @ApiOperation(value = "获取详细信息")
    public ResponseResult<UserInfo> getUserInfoDetail(HttpServletRequest req);

    @ApiOperation("修改用户信息")
    public ResponseResult<Void> modifyUserInfo(UserModify userModify, HttpServletRequest req);

    @ApiOperation("修改密码")
    public ResponseResult<Void> modifyPassword(@RequestBody UserPassword userPassword, HttpServletRequest req);

    @ApiOperation("修改头像")
    public ResponseResult<Void> modifyIcon(@RequestBody UserIcon userIcon, HttpServletRequest req);
}
