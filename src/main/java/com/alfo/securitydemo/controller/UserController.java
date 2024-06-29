package com.alfo.securitydemo.controller;

import com.alfo.securitydemo.domain.po.User;
import com.alfo.securitydemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public List<User> getList() {
        log.info("获取用户列表");
        return userService.list();
    }

    @ApiOperation("添加用户")
    @PostMapping("/add")
    public void add(@RequestBody User user) {
        log.info("添加用户");
        userService.saveUserDetails(user);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public Map index() {
        log.info("getUserInfo");

        SecurityContext context = SecurityContextHolder.getContext();   //存储认证对象的上下文
        Authentication authentication = context.getAuthentication();    //认证对象
        String username = authentication.getName(); //用户名
        Object principal = authentication.getPrincipal();   //身份
        Object credentials = authentication.getCredentials();   //凭证
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();   //权限

        log.info("username:{}", username);
        log.info("principal:{}", principal);
        log.info("credentials:{}", credentials);
        log.info("authorities:{}", authorities);

        //创建结果对象
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", username);

        return result;
    }

}
