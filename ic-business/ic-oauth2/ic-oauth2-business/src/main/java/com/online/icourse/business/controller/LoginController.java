package com.online.icourse.business.controller;

import com.online.icourse.business.dto.LoginParam;
import com.online.icourse.business.dto.Oauth2Response;
import com.online.icourse.business.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName LoginController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/27 0027
 * @Version V1.0
 **/
@RestController
public class LoginController {
    @Autowired
    OauthService oauthService;

    @PostMapping("user/login")
    public Oauth2Response<Map<String,Object>> login(@RequestBody LoginParam loginParam){
        return oauthService.login(loginParam);
    }

    @PostMapping("user/logout")
    public ResponseEntity<String> logout(){
//        return oauthService.logout("李四");
        return ResponseEntity.ok("退出登录");
    }

    @GetMapping("/user/echo/{s}")
    public ResponseEntity<String> echo(@PathVariable  String s){
        return ResponseEntity.ok("hello"+s);
    }




}
