package com.alfo.securitydemo.config;

import cn.hutool.json.JSONUtil;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        //创建结果对象
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", "该账号已经在其他设备登录");

        //转换为json
        String json = JSONUtil.toJsonStr(result);

        //返回响应
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
