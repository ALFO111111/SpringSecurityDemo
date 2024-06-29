package com.alfo.securitydemo.config;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取用户身份信息
        Object principal = authentication.getPrincipal();   //获取用户信息
        Object credentials = authentication.getCredentials();   //获取凭证信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();   //获取权限信息

        //创建结果对象
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("data", principal);

        //转换为json字符串
        String json = JSONUtil.toJsonStr(result);

        //返回响应
        response.setContentType("application/json;charset=UTF-8");  //设置响应头
        response.getWriter().println(json); //打印结果
    }
}
