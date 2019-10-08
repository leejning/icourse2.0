package com.online.icourse.business.dto;

import com.online.icourse.common.model.response.Response;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Oauth2Response
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/27 0027
 * @Version V1.0
 **/
@Data
@ToString
@NoArgsConstructor
public class Oauth2Response<T> implements Response {
    T data;
    int code;

    Oauth2Response(T data,int code){
        this.data = data;
        this.code = code;
    }
    Oauth2Response(int code){
        this.code = code;
    }

    public static Oauth2Response SUCCESS(Object data){
        return new Oauth2Response(data,SUCCESS_CODE);
    }
    public static Oauth2Response SUCCESS(){
        return new Oauth2Response(SUCCESS_CODE);
    }

}
