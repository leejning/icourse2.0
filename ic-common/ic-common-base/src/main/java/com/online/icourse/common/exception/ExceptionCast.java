package com.online.icourse.common.exception;


import com.online.icourse.common.model.response.ResultCode;


public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new ICourseException(resultCode);
    }
}
