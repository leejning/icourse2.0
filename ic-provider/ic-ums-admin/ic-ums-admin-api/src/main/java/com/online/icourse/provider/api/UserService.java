package com.online.icourse.provider.api;

import com.online.icourse.business.dto.UserIcon;
import com.online.icourse.business.dto.UserModify;
import com.online.icourse.business.dto.UserPassword;
import com.online.icourse.provider.bean.IUser;

public interface UserService {
    public IUser insert(IUser iUser);

    IUser findById(Long id);

    IUser findDetailInfoByUsername(String username);

    IUser findDetailByUsername(String username);

    Boolean checkExistByUsername(String username);

    Boolean ModifyUserInfo(UserModify userModify);

    Boolean ModifyIcon(UserIcon userIcon);

    Boolean ModifyPassword(UserPassword userPassword);
}
