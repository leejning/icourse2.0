package com.online.icourse.common.upload.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName UploadImageApi
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0
 **/
@Api(value="图片上传业务接口",consumes = "图片传业务")
public interface UploadImageApi {

    @ApiOperation(value="图片上传")
    public ResponseEntity<String> uploadImage(HttpServletRequest req);
}
