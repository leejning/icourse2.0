package com.online.icourse.provider.bean;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName StudentClazz
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@Entity
@Table(name = "ic_clazz_student")
@Data
public class StudentClazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studentId;
    private Long clazzId;
    private Long courseId;
    private Double score;
    private Integer onGroup;
}
