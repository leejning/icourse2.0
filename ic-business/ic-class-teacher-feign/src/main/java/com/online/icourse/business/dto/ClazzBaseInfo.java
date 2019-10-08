package com.online.icourse.business.dto;

import lombok.Data;

/**
 * @ClassName ClazzBaseInfo
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class ClazzBaseInfo {
    Long ClazzBaseId;
    String clazzBaseName;
    private String description;
}
