package com.h.springboot_mybatis.config;
import com.h.springboot_mybatis.config.security.*;
import com.h.springboot_mybatis.config.security.filter.MyAbstractAuthenticationProcessingFilter;
import com.h.springboot_mybatis.config.settings.SecuritySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecuritySettings settings;  //包含Security的配置参数

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;    //注入权限不足处理器

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(settings.getPermitAll()).permitAll()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()           //其他所有请求都需要认证
                .and()
        .formLogin()
                .loginProcessingUrl(settings.getLoginProcessingUrl())   //登录页面发出登录请求的url
                .loginPage(settings.getLoginPage()).permitAll()         //登录页面的url
                //.successForwardUrl("/car/Test")     //登录成功后请求的url
                .and()
        .logout()
                .logoutUrl(settings.getLogOut())                        //登出的url
                .logoutSuccessUrl(settings.getLogOutSuccessUrl())       //登出成功的url
                .and();
        http.csrf().disable();


        http
             .authenticationProvider(myAuthenticationProvider())     //将自定义的登录认证和自定义的登录过滤器加入Security的过滤器链
             .addFilterBefore(myAbstractAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

        http
             .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }

    //指定账户信息从哪里加载，与加密方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          //auth.userDetailsService(myUserDetailsService()).passwordEncoder(passwordEncoder());
//        auth.authenticationProvider(myAuthenticationProvider());
    }

    //注入自定义的UserDetailsService，用于加载用户信息
    @Bean
    protected MyUserDetailsService myUserDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //注入自定义的登录过滤器，用于拦截登录请求，验证认证参数
    @Bean
    protected MyAbstractAuthenticationProcessingFilter myAbstractAuthenticationProcessingFilter() throws Exception {
        MyAbstractAuthenticationProcessingFilter myAbstractAuthenticationProcessingFilter = new MyAbstractAuthenticationProcessingFilter();
        myAbstractAuthenticationProcessingFilter.setAuthenticationManager(super.authenticationManagerBean());
        //设置自定义认证成功处理器，和认证失败处理器
        myAbstractAuthenticationProcessingFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler());
        myAbstractAuthenticationProcessingFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler());
        return myAbstractAuthenticationProcessingFilter;
    }

    //注入自定义的AuthenticationProvider，用于账号和密码的认证
    @Bean
    protected MyAuthenticationProvider myAuthenticationProvider(){
        return  new MyAuthenticationProvider();
    }

    //注入自定义认证成功处理器,并传入认证成功后默认请求的url
    @Bean
    protected MyAuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MyAuthenticationSuccessHandler(settings.getSuccessForwardUrl());
    }

    //注入认证失败处理器
    @Bean
    protected MyAuthenticationFailureHandler myAuthenticationFailureHandler(){
        return new MyAuthenticationFailureHandler();
    }


}
