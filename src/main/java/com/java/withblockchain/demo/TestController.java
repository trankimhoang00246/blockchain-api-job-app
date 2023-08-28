package com.java.withblockchain.demo;

import com.java.withblockchain.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class TestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/get-address")
    public ResponseEntity getAddress() {
            return new ResponseEntity<>(SecurityUtils.getAddressOfPrincipal(), HttpStatus.OK);
    }
}
