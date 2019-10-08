package com.online.icourse.provider.bean.dto;

import com.online.icourse.common.model.dto.QueryDto;
import lombok.Data;

/**
 * @ClassName ClazzStudentQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@Data
public class ClazzStudentQueryDto extends QueryDto {
    Long clazzId;
    Integer group;

}
