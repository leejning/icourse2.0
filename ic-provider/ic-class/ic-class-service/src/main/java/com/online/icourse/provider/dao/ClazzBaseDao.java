package com.online.icourse.provider.dao;

import com.online.icourse.provider.bean.ClazzBase;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @ClassName ClazzBaseDao
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
public interface ClazzBaseDao extends PagingAndSortingRepository<ClazzBase,Long>, JpaSpecificationExecutor<ClazzBase> {
}
