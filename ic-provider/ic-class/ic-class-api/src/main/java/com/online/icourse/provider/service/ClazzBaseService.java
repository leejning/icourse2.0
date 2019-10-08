package com.online.icourse.provider.service;

import com.online.icourse.business.dto.ClazzBaseInfo;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.ClazzBase;
import com.online.icourse.provider.bean.dto.ClazzBaseQueryDto;

import java.util.List;

/**
 * @ClassName ClazzBaseService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
public interface ClazzBaseService {
    ClazzBase createClazzBase(ClazzBase clazzBase);

    QueryResult<ClazzBase> fingByjpa(ClazzBaseQueryDto clazzQueryDto);

    Boolean modifyClazzBase(ClazzBaseInfo clazzBaseInfo);

    ClazzBase deleteClazzBase(Long clazzId);

    DubboResponse<Long> exsistClazz(List<Long> classIds);
}
