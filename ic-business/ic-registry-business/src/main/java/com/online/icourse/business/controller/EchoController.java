package com.online.icourse.business.controller;

import com.online.icourse.common.exception.ExceptionCast;
import com.online.icourse.common.model.response.CommonCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @GetMapping("/echo/{string}")
    public ResponseEntity echo(@PathVariable String string){
        ExceptionCast.cast(CommonCode.SERVER_ERROR);
        return ResponseEntity.ok(string);
    }
}
