package com.online.icourse.provider.bean;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Course
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Entity
@Table(name = "ic_course")
@Data
@ToString
public class Course implements Serializable {
    private static final long serialVersionUID = -435853666952892717L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String courseType;
    private String teacherName;
    private Long teacherId;
    private String courseCover;


}
