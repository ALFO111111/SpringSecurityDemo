package com.alfo.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity  //开启SpringSecurity功能
public class WebSecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //创建基于内存的用户信息管理器
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        //使用inMemoryUserDetailsManager管理UserDetails对象
//        inMemoryUserDetailsManager.createUser(
//                //创建UserDetails对象，用于管理用户名、密码、用户角色、用户权限等内容
//                User.withDefaultPasswordEncoder().username("alfo").password("1234").roles("USER").build()
//        );
//        return inMemoryUserDetailsManager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        //基于数据库的用户信息管理器
//        return new DBUserDetailsManager();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //开启授权保护
        http
//                .authorizeHttpRequests(authorize -> authorize   //认证http请求
//                        .anyRequest()
//                        .authenticated()       //anyRequest()表示对所有请求进行认证过滤，authenticated()表示已认证过的请求自动授权
//                )
                .formLogin(form -> {
                    form.loginPage("/login")
//                            .permitAll()   //无需授权即可访问
                            .usernameParameter("myusername")    //默认是username，修改为myusername
                            .passwordParameter("mypassword")  //默认是password，修改为mypassword
                            .failureUrl("/login?failure")  //认证失败后跳转到/login?failure，默认是/login?error
                            .successHandler(new MyAuthenticationSuccessHandler())  //响应成功处理
                            .failureHandler(new MyAuthenticationFailureHandler());  //响应失败处理
                });  //使用表单授权方式
//                .httpBasic(withDefaults()); //使用基本认证（每个浏览器会提供对应的弹窗让我们去认证），一般不用
        //关闭csrf跨域保护
        http.csrf().disable();

        http.logout(logout -> {
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler());  //注销成功时的处理
        });

        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());   //请求未认证的接口
        });

        http.cors(withDefaults());   //解决跨域问题

        http.sessionManagement(session -> {
            session
                    .maximumSessions(1) //设置最大允许的session数量，设置为1说明只允许一个用户登录
                    .expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });

        return http.build();
    }

}
