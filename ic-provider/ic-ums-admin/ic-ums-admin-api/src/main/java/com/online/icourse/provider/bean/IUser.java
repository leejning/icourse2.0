package com.online.icourse.provider.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ic_user")
@Data
@ToString
public class IUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    /**
     * 头像
     */
    private String icon;

    private String email;
    private String nickname;
    private String role;
    private Long roleId;
    private Boolean auth;
    /**
     * 用户备注
     */
    private String note;
    /**
     * 账户可用性
     */
    private Boolean enable;
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registryTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
}
