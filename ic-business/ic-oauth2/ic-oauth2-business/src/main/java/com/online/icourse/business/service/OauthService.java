package com.online.icourse.business.service;


import com.google.common.collect.Maps;
import com.online.icourse.business.dto.AuthToken;
import com.online.icourse.business.dto.LoginParam;
import com.online.icourse.business.dto.Oauth2Response;
import com.online.icourse.common.exception.ExceptionCast;
import com.online.icourse.common.server.IcServiceList;
import com.online.icourse.provider.api.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/27 0027
 * @Version V1.0
 **/
@Service
public class OauthService {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${icourse.auth.tokenValiditySeconds}")
    private Long tokenValiditySeconds;

    @Value("${icourse.auth.clientId}")
    private String clientId;

    @Value("${icourse.auth.clientSecret}")
    private String clientSecret;

    private static String USER_TOKEN_INFO_PREFIX = "ic_token_user_id:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Reference(version = "2.0.0")
    private UserService userService;

    public Oauth2Response<Map<String, Object>> login(LoginParam loginParam) {
        Boolean exist = userService.checkExistByUsername(loginParam.getUsername());
        if(!exist){
            ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOTEXISTS);
        }
        //请求spring security申请令牌
        AuthToken authToken = this.applyToken(loginParam.getUsername(), loginParam.getPassword(), clientId, clientSecret);
        if (authToken == null) {
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_FAIL);
        }

        boolean res = saveToken2Redis(authToken, loginParam.getUsername());
        if (!res) {
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_TOKEN_SAVEFAIL);
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("jti", authToken.getJti());
        map.put("token", authToken.getJwt_token());
        return Oauth2Response.SUCCESS(map);
    }

    private boolean saveToken2Redis(AuthToken authToken, String username) {
        String key = USER_TOKEN_INFO_PREFIX + username;
        redisTemplate.boundValueOps(key).set(authToken.getJwt_token(),tokenValiditySeconds, TimeUnit.SECONDS);
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire>0;
    }


    /**
     * 发送http请求，申请令牌
     *
     * @MethodName: applyToken
     * @Description: TODO
     * @Param: [username, password, clientId, clientSecret]
     * @Return: com.online.icourse.business.dto.AuthToken
     * @Author: Administrator
     * @Date: 2019/9/28 0028
     **/
    private AuthToken applyToken(String username, String password, String clientId, String clientSecret) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(IcServiceList.IC_BUSINESS_OAUTH);
        URI uri = serviceInstance.getUri();
        String authUrl = uri + "/oauth/token";
        //定义header
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic(clientId, clientSecret);
        header.add("Authorization", httpBasic);

        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);

        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);

        //申请令牌信息
        Map bodyMap = exchange.getBody();
        if (bodyMap == null ||bodyMap.get("jti") == null||
                bodyMap.get("access_token") == null ||
                bodyMap.get("refresh_token") == null) {
            if ("invalid_grant".equals(bodyMap.get("error"))) {
                ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
            } else if ("unauthorized".equals(bodyMap.get("error"))) {
                ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOTEXISTS);
            }

            return null;
        }
        AuthToken authToken = new AuthToken();
        authToken.setRefresh_token((String) bodyMap.get("refresh_token"));//刷新令牌
        authToken.setJwt_token((String) bodyMap.get("access_token"));
        authToken.setJti((String) bodyMap.get("jti"));//jti
        return authToken;
    }

    //获取httpbasic的串
    private String getHttpBasic(String clientId, String clientSecret) {
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }

    public Oauth2Response logout(String username) {

        return Oauth2Response.SUCCESS();
    }
}
