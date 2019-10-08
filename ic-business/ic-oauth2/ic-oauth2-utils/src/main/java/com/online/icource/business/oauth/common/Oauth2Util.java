package com.online.icource.business.oauth.common;

import com.online.icourse.common.utils.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by mrt on 2018/5/25.
 */
public class Oauth2Util {
    public static Long getLoginStuId(HttpServletRequest request){
        return 38L;
    }
    public static Long getLoginTeaId(HttpServletRequest request){
//        Map<String, Object> jwtClaimsFromHeader = getJwtClaimsFromHeader(request);
//        return (Long) jwtClaimsFromHeader.get("id");
        return 1L;
    }
    public static Long getLoginUserId(HttpServletRequest request){
//        Map<String, Object> jwtClaimsFromHeader = getJwtClaimsFromHeader(request);
//        return (Long) jwtClaimsFromHeader.get("id");
        return 1L;
    }
    public static Map<String, Object> getJwtClaimsFromHeader(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        //取出头信息
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization) || authorization.indexOf("Bearer") < 0) {
            return null;
        }
        //从Bearer 后边开始取出token
        String token = authorization.substring(7);
        Map<String, Object> map = null;
        try {
            //解析jwt
            Jwt decode = JwtHelper.decode(token);
            //得到 jwt中的用户信息
            String claims = decode.getClaims();
            //将jwt转为Map

            map = MapperUtils.json2map(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
