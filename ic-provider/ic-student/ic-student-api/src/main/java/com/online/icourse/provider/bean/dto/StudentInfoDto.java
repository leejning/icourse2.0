package com.online.icourse.provider.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName StudentInfoDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoDto {
    private Long id;
    private Long studentNo;
    private String studentName;
    private String className;
    private Integer grade;
}
