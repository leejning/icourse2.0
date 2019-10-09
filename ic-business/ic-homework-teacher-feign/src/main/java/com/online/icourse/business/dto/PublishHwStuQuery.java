package com.online.icourse.business.dto;

import com.online.icourse.common.model.dto.QueryDto;
import lombok.Data;

/**
 * @ClassName PublishHwStuQuery
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/7 0007
 * @Version V1.0
 **/
@Data
public class PublishHwStuQuery extends QueryDto {
    String hpId;
    Long clazzId;
    Boolean finish;
    String studentName;

}
