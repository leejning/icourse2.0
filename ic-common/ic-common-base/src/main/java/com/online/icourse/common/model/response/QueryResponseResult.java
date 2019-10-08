package com.online.icourse.common.model.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult<T> {

    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode,QueryResult<T> queryResult){
        super(resultCode);
       this.queryResult = queryResult;
    }

    public static <T> QueryResponseResult<T> SUCCESS(QueryResult<T> queryResult){
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

}
