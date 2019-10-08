package com.online.icourse.provider.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Student
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Entity
@Table(name = "ic_student")
@Data
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = -5981923807800698912L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentNo;
    private String studentName;

    private String className;
    private Integer grade;

    private Long userId;

    public Student(Long id ,Long studentNo, String studentName, String className, Integer grade) {
        this.id = id;
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.className = className;
        this.grade = grade;
    }
}
