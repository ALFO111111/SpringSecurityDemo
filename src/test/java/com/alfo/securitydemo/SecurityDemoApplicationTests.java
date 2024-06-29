package com.alfo.securitydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

@SpringBootTest
class SecurityDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testPassword() {
        //工作因子，默认是10，最小值是4，最大值为31，值越大运算速度越慢
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        //明文“passowrd”
        //密文：result，即使每次密码相同，每次生成的密文也不一致
        String result = encoder.encode("password");
        System.out.println(result);

        //密码校验
        Assert.isTrue(encoder.matches("password", result), "密码不一致");
    }

}
