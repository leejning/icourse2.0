package com.online.icourse.business.controller;

import com.online.icourse.business.api.RegControllerApi;
import com.online.icourse.common.model.response.CommonCode;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.api.UserService;
import com.online.icourse.provider.bean.IUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegController implements RegControllerApi {
    @Reference(version = "2.0.0")
    private UserService userService;

    @Override
    @PostMapping("registry")
    public ResponseResult<IUser> registry(@RequestBody @Validated IUser iUser){
        iUser = userService.insert(iUser);
        if(iUser!=null) {
            return ResponseResult.SUCCESS(iUser);
        }
        else{
            return ResponseResult.FAIL();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseResult<IUser> getUserById(@PathVariable Long id){
        IUser user = userService.findById(id);
        return new ResponseResult<>(CommonCode.SUCCESS,user);
    }



}
