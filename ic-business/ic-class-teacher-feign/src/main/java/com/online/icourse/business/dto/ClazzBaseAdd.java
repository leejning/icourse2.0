package com.online.icourse.business.dto;

import lombok.Data;

/**
 * @ClassName ClazzBaseAdd
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class ClazzBaseAdd {
    String clazzBaseName;
    Long courseId;
    String description;
}
