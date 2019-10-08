package com.online.icourse.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName ProfileApplication
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/28 0028
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = "com.online.icourse.common")
public class ProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class,args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 配置默认的加密方式
        return new BCryptPasswordEncoder();
    }
}
