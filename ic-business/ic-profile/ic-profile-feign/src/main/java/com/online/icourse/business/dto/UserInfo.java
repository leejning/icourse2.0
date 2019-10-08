package com.online.icourse.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName UserInfo
 * @Description: TODO  用户信息查询对象
 * @Author Administrator
 * @Date 2019/9/28 0028
 * @Version V1.0
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String username;
    private String nickname;
    private String icon;
    private Date registryTime;
    private Date lastLoginTime;
    private String note;
    private String email;
}
