package com.online.icourse.provider.Dao;

import com.online.icourse.provider.bean.IUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserDao extends PagingAndSortingRepository<IUser,Long>, JpaRepository<IUser,Long> {
    Optional<IUser> findByUsername(String username);

    Optional<IUser> findIdByUsername(String username);

    @Modifying
    @Query("update IUser set icon = ?2 where username = ?1")
    int modifyIcon(String username, String icon);

    @Modifying
    @Query("update IUser set password = ?2 where username = ?1")
    int modifyPassword(String username, String password);

    @Query("select password from IUser where username = ?1")
    String findPasswordByUsername(String username);
}
