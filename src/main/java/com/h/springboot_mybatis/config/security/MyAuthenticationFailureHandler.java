package com.h.springboot_mybatis.config.security;

import com.alibaba.fastjson.JSON;
import com.h.springboot_mybatis.config.settings.SecuritySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private SecuritySettings settings;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败！");
        if (settings.getLoginType().equals("JSON")) {
            response.setContentType("application/json;charset=UTF-8");
            //HttpStatus.INTERNAL_SERVER_ERROR.value(),
            //在这里将异常信息响应到浏览器
            response.getWriter().write(exception.getMessage());

        } else {
            response.setContentType("text/html;charset=UTF-8");
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
