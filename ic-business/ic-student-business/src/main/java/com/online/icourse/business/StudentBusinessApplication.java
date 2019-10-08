package com.online.icourse.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName StudentBusinessApplication
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@SpringBootApplication
@ComponentScan(value = "com.online.icourse.common")
@ComponentScan(value = "com.online.icourse.business")
public class StudentBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentBusinessApplication.class,args);
    }
}
