package com.online.icourse.provider.controller;

import com.online.icourse.provider.api.UserService;
import com.online.icourse.provider.bean.IUser;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RegControllerTest {
    @Reference(version = "2.0.0")
    private UserService userService;
    @Test
    public void registry() {
        IUser iUser = new IUser();
        iUser.setPassword("$2a$10$WtAtXVO9hP91EUDBQjlqOeGXOO7BMU74CQYyX4yK9M9kMQRWmDDbW");
        iUser.setUsername("张三");
        userService.insert(iUser);
    }

    @Test
    public void getUserById(){
        IUser user = userService.findById(1l);
        System.out.println(user.getUsername());
    }
}