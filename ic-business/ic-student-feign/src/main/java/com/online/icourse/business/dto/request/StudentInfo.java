package com.online.icourse.business.dto.request;

import lombok.Data;

/**
 * @ClassName StudentInfo
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class StudentInfo {
    String studentName;
    String className;
    Integer grade;
}

