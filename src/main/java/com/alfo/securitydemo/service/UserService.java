package com.alfo.securitydemo.service;

import com.alfo.securitydemo.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    void saveUserDetails(User user);
}
