package com.alfo.securitydemo.service.impl;

import com.alfo.securitydemo.config.DBUserDetailsManager;
import com.alfo.securitydemo.domain.po.User;
import com.alfo.securitydemo.mapper.UserMapper;
import com.alfo.securitydemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        dbUserDetailsManager.createUser(new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                "{bcrypt}" + new BCryptPasswordEncoder().encode(user.getPassword()),
                user.getEnabled(),
                true,   //用户账号是否过期
                true,   //用户凭证是否过期
                true,   //用户是否可用（是否被锁定）
                authorities //权限列表
        ));
    }
}
