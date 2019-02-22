package com.hundsun.accountingsystem.Global.config;

import com.hundsun.accountingsystem.Global.security.CustomUserService;
import com.hundsun.accountingsystem.Global.util.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) //启用Security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new CustomUserService();
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){

            //使用MD5获取加密之后的密码
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            //验证密码
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
            }}); //user Details Service验证
    }

    //定制授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()  //首页任意访问
                .antMatchers("/user").permitAll()  //注册任意访问
                .antMatchers("/druid").permitAll()  //druid任意访问
                .antMatchers("/getuser").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin().usernameParameter("username").passwordParameter("password")
                .loginPage("/login")
                .defaultSuccessUrl("/", true)//登录成功之后跳转首页
                .failureUrl("/login?error")
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll()//注销行为任意访问
        ;
    }

    /**
     * 配置.忽略的静态文件，不加的话，登录之前页面的css,js不能正常使用，得登录之后才能正常.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        web.ignoring().antMatchers("/img/*.jpg","/**/*.js", "/lang/*.json", "/**/*.css", "/**/*.js", "/**/*.map",
            "/**/*.html", "/**/*.png"); }

}

