package com.online.icourse.provider.service;

import com.online.icourse.provider.Dao.UserDao;
import com.online.icourse.provider.bean.IUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IUserServiceImplTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void insert() {
        IUser iUser = new IUser();
        iUser.setPassword("$2a$10$WtAtXVO9hP91EUDBQjlqOeGXOO7BMU74CQYyX4yK9M9kMQRWmDDbW");
        iUser.setUsername("xiaoming");
        userDao.save(iUser);
    }
    @Test
    public void findByUsername(){
        Optional<IUser> opt = userDao.findByUsername("李四");
        if(opt.isPresent())
            System.out.println(opt.get().getUsername());
    }

    @Test
    public void checkExistByUsername(){
        Optional<IUser> opt = userDao.findIdByUsername("李四");
        System.out.println(opt);
        System.out.println(opt.isPresent());
    }

    @Test
    @Transactional
    public void modifyPasswprd(){
        int res = userDao.modifyIcon("Csasc","15634313");
        System.out.println(res);
    }

}