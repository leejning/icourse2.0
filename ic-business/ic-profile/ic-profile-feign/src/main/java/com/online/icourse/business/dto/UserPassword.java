package com.online.icourse.business.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName UserPassword
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/28 0028
 * @Version V1.0
 **/
@Data
public class UserPassword {
    @Length(min=6,max = 15,message = "用户新密码长度需要在6-15位之间")
    @NotBlank
    private String newPassword;
    @Length(min=6,max = 15,message = "用户旧密码长度需要在6-15位之间")
    @NotBlank
    private String oldPassword;
    @Length(min=6,max = 15,message = "用户新密码长度需要在6-15位之间")
    @NotBlank
    private String confirmPassword;
    private String username;
}
