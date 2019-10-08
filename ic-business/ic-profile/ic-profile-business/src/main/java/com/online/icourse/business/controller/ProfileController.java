package com.online.icourse.business.controller;

import com.online.icource.business.oauth.common.Oauth2Util;
import com.online.icourse.business.api.ProfileControllerApi;
import com.online.icourse.business.common.ProfileCode;
import com.online.icourse.business.dto.UserIcon;
import com.online.icourse.business.dto.UserInfo;
import com.online.icourse.business.dto.UserModify;
import com.online.icourse.business.dto.UserPassword;
import com.online.icourse.common.exception.ExceptionCast;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.api.UserService;
import com.online.icourse.provider.bean.IUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName ProfileController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/28 0028
 * @Version V1.0
 **/
@RestController
@RequestMapping("profile")
public class ProfileController implements ProfileControllerApi {

    @Reference(version = "2.0.0")
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("user/info")
    public ResponseResult<UserInfo> getUserInfo(HttpServletRequest req) {
        Map<String, Object> jwtUser = Oauth2Util.getJwtClaimsFromHeader(req);
        UserInfo user = new UserInfo();
        user.setUsername((String) jwtUser.get("user_name"));
        user.setIcon((String) jwtUser.get("icon"));
        return ResponseResult.SUCCESS(user);
    }

    @Override
    @GetMapping("user/info/detail")
    public ResponseResult<UserInfo> getUserInfoDetail(HttpServletRequest req) {
        Map<String, Object> jwtUser = Oauth2Util.getJwtClaimsFromHeader(req);
        System.out.println(jwtUser);
        IUser user = userService.findDetailInfoByUsername((String) jwtUser.get("user_name"));
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        return ResponseResult.SUCCESS(userInfo);
    }

    @Override
    @PutMapping("user/info")
    public ResponseResult<Void> modifyUserInfo(@RequestBody UserModify userModify, HttpServletRequest req) {
        ResponseResult responseResult = new ResponseResult();
        userModify.setUsername(getLoginUsername(req));
        Boolean res = userService.ModifyUserInfo(userModify);
        if (res) {
            return ResponseResult.SUCCESS("修改信息成功");
        } else {
            return ResponseResult.FAIL(ProfileCode.PROFILE_MODIFY_INFO_FAIL);
        }
    }

    @Override
    @PutMapping("user/password")
    public ResponseResult<Void> modifyPassword(@RequestBody UserPassword userPassword, HttpServletRequest req) {
        if(!userPassword.getNewPassword().equals(userPassword.getConfirmPassword())){
            ExceptionCast.cast(ProfileCode.PROFILE_PASSWORD_NOT_SAME);
        }

        userPassword.setUsername(getLoginUsername(req));
        Boolean res = userService.ModifyPassword(userPassword);
        if (res) {
            return ResponseResult.SUCCESS("修改密码成功");
        } else {
            return ResponseResult.FAIL(ProfileCode.PROFILE_MODIFY_PASSWORD_FAIL);
        }
    }

    @Override
    @PutMapping("user/icon")
    public ResponseResult<Void> modifyIcon(@RequestBody UserIcon userIcon, HttpServletRequest req) {
        userIcon.setUsername(getLoginUsername(req));
        Boolean res = userService.ModifyIcon(userIcon);
        if (res) {
            return ResponseResult.SUCCESS("修改头像成功");
        } else {
            return ResponseResult.FAIL(ProfileCode.PROFILE_MODIFY_PASSWORD_FAIL);
        }
    }


    private String getLoginUsername(HttpServletRequest req) {
        return (String) Oauth2Util.getJwtClaimsFromHeader(req).get("user_name");
    }


}
