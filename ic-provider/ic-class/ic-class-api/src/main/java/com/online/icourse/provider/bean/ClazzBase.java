package com.online.icourse.provider.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName ClazzBase
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
@Entity
@Table(name = "ic_clazz")
public class ClazzBase implements Serializable {
    private static final long serialVersionUID = 4758426572620045254L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clazzBaseName;
    private Long courseId;
    private String description;

}
