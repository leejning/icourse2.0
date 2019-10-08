package com.online.icourse.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName HomeworkTeacherBusinessApplication
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@SpringBootApplication
@ComponentScan(value = "com.online.icourse.common")
@ComponentScan(value = "com.online.icourse.business")
public class HomeworkTeacherBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkTeacherBusinessApplication.class,args);
    }
}
