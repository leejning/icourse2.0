package com.online.icourse.common.utils;

import com.online.icourse.common.model.PageAndSortObj;
import com.online.icourse.common.model.dto.QueryDto;
import com.online.icourse.common.model.response.QueryResult;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName HIbernatePageUtils
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
public class DataJpaPageUtils {
    static final Integer defaultPageNo = 1;
    static final Integer defaultPageSize = 20;


    public static PageAndSortObj initPageAndSort(QueryDto queryDto) {
        PageAndSortObj pageAndSortObj = new PageAndSortObj();
        Integer pageNo = queryDto.getPageNo();
        Integer pageSize = queryDto.getPageSize();
        if ( pageNo== null ||pageNo < 1) {
            queryDto.setPageNo(defaultPageNo);
            pageNo = defaultPageNo;
        }
        if (pageSize == null || pageSize < 1) {
            queryDto.setPageSize(defaultPageSize);
            pageSize = defaultPageSize;
        }
        String sortby = queryDto.getSortby();
        if ( sortby == null || sortby == "" || sortby.isEmpty()) {
            queryDto.setSortby("id");
            sortby = queryDto.getSortby();
        }
        if (!StringUtils.isEmpty(queryDto.getDescOrAsc()) && "asc".equals(queryDto.getDescOrAsc())) {
            pageAndSortObj.setSort(Sort.by(Sort.Direction.ASC, sortby)) ;
            queryDto.setDescOrAsc("asc");
        } else {
            pageAndSortObj.setSort(Sort.by(Sort.Direction.DESC, sortby));
            queryDto.setDescOrAsc("desc");
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, pageAndSortObj.getSort());
        pageAndSortObj.setPageable(pageable);
        return pageAndSortObj;
    }

    public static<T> QueryResult<T> setQueryResult(Page<T> data,QueryDto queryDto) {
        QueryResult<T> queryResult = new QueryResult();
        BeanUtils.copyProperties(queryDto,queryResult);
        queryResult.setList(data.getContent());
        queryResult.setTotal(data.getTotalElements());
        return queryResult;
    }
    public static<T> QueryResult<T> setQueryResult(List<T> data,Long total,QueryDto queryDto) {
        QueryResult<T> queryResult = new QueryResult();
        BeanUtils.copyProperties(queryDto,queryResult);
        queryResult.setList(data);
        queryResult.setTotal(total);
        return queryResult;
    }
}
