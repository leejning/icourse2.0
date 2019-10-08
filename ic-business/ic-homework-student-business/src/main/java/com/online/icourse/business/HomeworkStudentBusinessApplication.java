package com.online.icourse.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName HomeworkStudentBusinessApplication
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@SpringBootApplication
@ComponentScan("com.online.icourse.common")
@ComponentScan("com.online.icourse.business")
public class HomeworkStudentBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkStudentBusinessApplication.class,args);
    }
}
