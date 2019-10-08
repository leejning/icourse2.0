package com.online.icourse.business.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName StundetQueryHwParam
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/7 0007
 * @Version V1.0
 **/
@Data
public class StundetQueryHwParam {
    @NotBlank
    private Long clazzId;
    @NotBlank
    private Long studentId;
    private Boolean finish;
}
