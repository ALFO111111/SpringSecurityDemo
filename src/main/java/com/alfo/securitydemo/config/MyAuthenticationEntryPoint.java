package com.alfo.securitydemo.config;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //获取错误信息
        String localizedMessage = authException.getLocalizedMessage();

        //创建结果对象
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", "需要登录");

        //转换为json字符串
        String json = JSONUtil.toJsonStr(result);

        //返回响应
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
