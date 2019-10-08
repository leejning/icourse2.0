package com.online.icourse.business.config.service;

import com.online.icourse.business.dto.JwtUser;
import com.online.icourse.provider.api.UserService;
import com.online.icourse.provider.bean.IUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/27 0027
 * @Version V1.0
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "$2a$10$WhCuqmyCsYdqtJvM0/J4seCU.xZQHe2snNE5VFUuBGUZWPbtdl3GG";
    private static final String USER_PERMISSION = "USER";
    @Reference(version = "2.0.0")
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        IUser user = userService.findDetailByUsername(username);
        if (user == null) {
            return null;
        }
        JwtUser jwtUser = new JwtUser(user.getUsername(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(USER_PERMISSION));
        jwtUser.setIcon(user.getIcon());
        return jwtUser;
    }

}
