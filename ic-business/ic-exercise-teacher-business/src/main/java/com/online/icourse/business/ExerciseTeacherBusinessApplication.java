package com.online.icourse.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ExerciseTeacherBusinessApplication
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/3 0003
 * @Version V1.0
 **/
@SpringBootApplication
@ComponentScan("com.online.icourse.common")
@ComponentScan("com.online.icourse.business")
public class ExerciseTeacherBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExerciseTeacherBusinessApplication.class,args);
    }
}
