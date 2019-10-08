package com.online.icourse.provider.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Chapter
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
@Entity
@Table(name = "ic_chapter")
@Data
public class Chapter implements Serializable {
    private static final long serialVersionUID = 3812353740945782665L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chapterName;
    private Long chapterPid;
    private Long courseId;
    private Boolean leaf;

}
