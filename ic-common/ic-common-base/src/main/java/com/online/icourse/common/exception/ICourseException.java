package com.online.icourse.common.exception;


import com.online.icourse.common.model.response.ResultCode;

/**
 * @ClassName ICourseException.java
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/27 0027
 * @Version V1.0.0
**/
public class ICourseException extends RuntimeException {

    //错误代码
    ResultCode resultCode;
    

    public ICourseException(ResultCode resultCode){
        this.resultCode = resultCode;
    }
    
    public ResultCode getResultCode(){
        return resultCode;
    }


}
