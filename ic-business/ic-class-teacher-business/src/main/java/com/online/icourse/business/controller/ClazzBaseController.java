package com.online.icourse.business.controller;

import com.online.icourse.business.api.ClazzBaseControllerApi;
import com.online.icourse.business.dto.ClazzBaseAdd;
import com.online.icourse.business.dto.ClazzBaseInfo;
import com.online.icourse.common.code.ClazzCode;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.ClazzBase;
import com.online.icourse.provider.bean.dto.ClazzBaseQueryDto;
import com.online.icourse.provider.service.ClazzBaseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ClazzBaseController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@RestController
@RequestMapping("clazzbase")
public class ClazzBaseController implements ClazzBaseControllerApi {
    @Reference(version = "2.0.0")
    private ClazzBaseService clazzBaseService;

    @PostMapping
    @Override
    public ResponseResult<ClazzBase> createClazz(@RequestBody ClazzBaseAdd clazzBaseAdd) {
        ClazzBase clazzBase = new ClazzBase();
        BeanUtils.copyProperties(clazzBaseAdd, clazzBase);
        ClazzBase base = clazzBaseService.createClazzBase(clazzBase);
        return ResponseResult.SUCCESS(base);
    }

    @GetMapping("list")
    @Override
    public QueryResponseResult<ClazzBase> getClazzList(ClazzBaseQueryDto clazzQueryDto) {
        QueryResult<ClazzBase> queryResult = clazzBaseService.fingByjpa(clazzQueryDto);
        return QueryResponseResult.SUCCESS(queryResult);
    }

    @PutMapping("{clazzBaseId}")
    @Override
    public ResponseResult<ClazzBase> modifyClazzInfo(@PathVariable Long clazzBaseId, @RequestBody ClazzBaseInfo clazzBaseInfo) {
        clazzBaseInfo.setClazzBaseId(clazzBaseId);
        Boolean res = clazzBaseService.modifyClazzBase(clazzBaseInfo);
        if (!res) {
            ResponseResult.FAIL(ClazzCode.MODIFY_CLAZZ_BASE_FAIL);
        }
        return ResponseResult.SUCCESS("修改课堂基础信息成功");
    }

    @DeleteMapping("{clazzBaseId}")
    @Override
    public ResponseResult removeCalzz(Long clazzBaseId) {
        ClazzBase base = clazzBaseService.deleteClazzBase(clazzBaseId);
        if (base == null) {
            ResponseResult.FAIL(ClazzCode.REMOVE_CLAZZ_BASE_FAIL);
        }
        return ResponseResult.SUCCESS(base);
    }
}
