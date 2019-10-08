package com.online.icourse.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ClassManagerBusinessApplication
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
@SpringBootApplication
@ComponentScan(value = "com.online.icourse.common.config")
@ComponentScan(value = "com.online.icourse.business")
public class ClassTeacherBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClassTeacherBusinessApplication.class,args);
    }
}
