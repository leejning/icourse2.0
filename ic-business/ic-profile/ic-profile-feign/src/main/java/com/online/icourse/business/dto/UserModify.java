package com.online.icourse.business.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * @ClassName UserModify
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/28 0028
 * @Version V1.0
 **/
@Data
public class UserModify {
    private String note;
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
            ,message = "请输入正确的邮箱地址")
    @NotBlank
    private String email;
    @Length(min = 3,max = 8,message = "昵称长度要在3-8个字之间")
    @NotBlank
    private String nickname;
    private String username;
}
