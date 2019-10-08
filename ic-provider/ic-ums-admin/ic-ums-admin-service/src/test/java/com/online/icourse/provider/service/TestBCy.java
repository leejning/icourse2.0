package com.online.icourse.provider.service;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName TestBCy
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0
 **/
public class TestBCy {

    @Test
    public void test(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean res = bCryptPasswordEncoder.matches("123456", "$2a$10$WtAtXVO9hP91EUDBQjlqOeGXOO7BMU74CQYyX4yK9M9kMQRWmDDbW");
        System.out.println(res);
    }
}
