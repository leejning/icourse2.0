package com.online.icourse.provider.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName ClazzStudent
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@Entity
@Table(name = "ic_clazz_student")
@Data
public class ClazzStudent implements Serializable {

    private static final long serialVersionUID = 6130675314328181979L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long studentId;
    private Double score;
    private Integer onGroup;
}
