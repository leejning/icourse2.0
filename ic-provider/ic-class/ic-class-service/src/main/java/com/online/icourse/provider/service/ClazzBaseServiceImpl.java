package com.online.icourse.provider.service;

import com.online.icourse.business.dto.ClazzBaseInfo;
import com.online.icourse.common.code.ClazzCode;
import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.response.CommonCode;
import com.online.icourse.common.model.response.DubboResponse;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.utils.DataJpaPageUtils;
import com.online.icourse.provider.bean.ClazzBase;
import com.online.icourse.provider.bean.dto.ClazzBaseQueryDto;
import com.online.icourse.provider.dao.ClazzBaseDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ClazzBaseServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class ClazzBaseServiceImpl implements ClazzBaseService {
    @Autowired
    private ClazzBaseDao clazzBaseDao;


    @Override
    public ClazzBase createClazzBase(ClazzBase clazzBase) {
        return clazzBaseDao.save(clazzBase);
    }

    @Override
    public QueryResult<ClazzBase> fingByjpa(ClazzBaseQueryDto clazzQueryDto) {
        PageAndSortObj pageAndSortObj = DataJpaPageUtils.initPageAndSort(clazzQueryDto);
        Page<ClazzBase> data = clazzBaseDao.findAll(ClazzBaseQueryDto.getWhere(clazzQueryDto), pageAndSortObj.getPageable());
        return DataJpaPageUtils.setQueryResult(data, clazzQueryDto);
    }

    @Override
    public Boolean modifyClazzBase(ClazzBaseInfo clazzBaseInfo) {
        Optional<ClazzBase> opt = clazzBaseDao.findById(clazzBaseInfo.getClazzBaseId());
        if (opt.isPresent()) {
            ClazzBase base = opt.get();
            BeanUtils.copyProperties(clazzBaseInfo, base);
            clazzBaseDao.save(base);
            return true;
        }
        return false;
    }

    @Override
    public ClazzBase deleteClazzBase(Long clazzId) {
        Optional<ClazzBase> opt = clazzBaseDao.findById(clazzId);
        if (opt.isPresent()) {
            ClazzBase base = opt.get();
            clazzBaseDao.delete(base);
            return base;
        }
        return null;
    }

    @Override
    public DubboResponse<Long> exsistClazz(List<Long> classIds) {
        for (Long id : classIds) {
            if (!clazzBaseDao.existsById(id)) {
                return DubboResponse.FAIL(id, ClazzCode.NO_CLAZZ_IN_THIS_ID);
            }
        }
        return DubboResponse.SUCCESS(CommonCode.SUCCESS);
    }
}
