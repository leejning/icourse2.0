package com.online.icourse.common.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName UploadImageApplication
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.online.icourse.common.config"})
@ComponentScan(basePackages = {"com.online.icourse.common.upload"})
public class UploadImageApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadImageApplication.class, args);
    }
}
