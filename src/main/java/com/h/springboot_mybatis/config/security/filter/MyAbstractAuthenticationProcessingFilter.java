package com.h.springboot_mybatis.config.security.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的用户名密码认证过滤器
 */
@Component
public class MyAbstractAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private boolean postOnly = true;

    public  MyAbstractAuthenticationProcessingFilter(){
       //指定该过滤器对那个url进行拦截
       super(new AntPathRequestMatcher("/login","POST"));
   }


    protected MyAbstractAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected MyAbstractAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //判断是否是POST类型登录请求
        if(this.postOnly && !httpServletRequest.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }else{
            //获取参数
            String userName = httpServletRequest.getParameter(usernameParameter);
            String password = httpServletRequest.getParameter(passwordParameter);
            if(StringUtils.isEmpty(userName) && StringUtils.isEmpty(password)){
                throw new UsernameNotFoundException("MyAbstractAuthenticationProcessingFilter 获取认证信息失败 用户名或密码为空！");
            }
            //使用获取的认证信息创建一个未认证的Token
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName,password);

            usernamePasswordAuthenticationToken.setDetails(httpServletRequest);//?

            //通过AuthenticationManager调用自身的AuthenticationProvider方法进行认证
            return super.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        }
    }

}
