package com.online.icourse.common.model.dto;

import lombok.Data;

/**
 * @ClassName QueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class QueryDto {
    private Integer pageNo;
    /**
     * 页数
     */
    private Integer pageSize;

    private String descOrAsc;
    private String sortby;
}
