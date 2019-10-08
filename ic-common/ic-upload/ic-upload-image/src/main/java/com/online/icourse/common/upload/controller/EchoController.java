package com.online.icourse.common.upload.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EchoController
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/29 0029
 * @Version V1.0
 **/
@RestController
@Api(value = "asdfmlk",description = "输出")
public class EchoController {

    @GetMapping("/echo/{s}")
    @ApiOperation("输出接口")
    public String echo(@PathVariable String s){
        return "输出"+s;
    }
}
