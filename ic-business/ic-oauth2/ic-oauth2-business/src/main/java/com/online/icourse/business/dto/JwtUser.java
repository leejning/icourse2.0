package com.online.icourse.business.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @ClassName JwtUser
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/28 0028
 * @Version V1.0
 **/
@Data
@ToString
public class JwtUser extends User {
    private String user_name;
    private String icon;

   public JwtUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
       super(username, password, authorities);
   }
}
