package com.alfo.securitydemo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@Api(tags = "登录接口")
public class LoginController {

    @GetMapping("/")
    public String index() {
        log.info(SecurityContextHolder.getContext().getAuthentication().toString());
        log.info("转到index.html");
        return "index";
    }
}
