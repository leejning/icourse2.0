package com.online.icourse.provider.service;

import com.online.icourse.business.dto.UserIcon;
import com.online.icourse.business.dto.UserModify;
import com.online.icourse.business.dto.UserPassword;
import com.online.icourse.provider.Dao.UserDao;
import com.online.icourse.provider.api.UserService;
import com.online.icourse.provider.bean.IUser;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @ClassName UserServiceImpl.java
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/27 0027
 * @Version V1.0.0
 **/
@Service(version = "2.0.0")
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public IUser insert(IUser iUser) {
        iUser.setEnable(true);
        return userDao.save(iUser);
    }

    @Override
    public IUser findById(Long id) {
        Optional<IUser> opt = userDao.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }


    @Override
    public IUser findDetailInfoByUsername(String username) {
        Optional<IUser> opt = userDao.findByUsername(username);
        if (opt.isPresent()) {
            IUser user = opt.get();
            user.setPassword(null);
            return user;
        }
        return null;
    }

    @Override
    public IUser findDetailByUsername(String username) {
        Optional<IUser> opt = userDao.findByUsername(username);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public Boolean checkExistByUsername(String username) {
        Optional<IUser> opt = userDao.findIdByUsername(username);
        return opt.isPresent();
    }

    @Override
    @Transactional
    public Boolean ModifyUserInfo(UserModify userModify) {
        Optional<IUser> opt = userDao.findIdByUsername(userModify.getUsername());
        if (opt.isPresent()) {
            IUser user = opt.get();
            BeanUtils.copyProperties(userModify,user);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean ModifyIcon(UserIcon userIcon) {
        int res = userDao.modifyIcon(userIcon.getUsername(),userIcon.getIcon());
        return res>0;
    }

    @Override
    @Transactional
    public Boolean ModifyPassword(UserPassword userPassword) {
        String password = userDao.findPasswordByUsername(userPassword.getUsername());
        boolean res = bCryptPasswordEncoder.matches(userPassword.getOldPassword(), password);
        if(!res) {
            return false;
        }
        String encode = bCryptPasswordEncoder.encode(userPassword.getNewPassword());
        return userDao.modifyPassword(userPassword.getUsername(),encode)>0;
    }


}
