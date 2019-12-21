package com.h.springboot_mybatis.config.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "securityconfig")

public class SecuritySettings {
    private String loginPage;   //自定义登录URL
    private String loginType;   //登录方式
    private String loginProcessingUrl; //登录表单发送登录请求的url，若该属性不配置，则默认和loginPage属性相同
    private String successForwardUrl; //登录成功后请求的url
    private String permitAll;   //完全允许访问的URL
    private String logOut;  //默认的登出
    private String logOutSuccessUrl;    //设置登出成功的链接
    private String rememberMe;  //用来记住用户的登录状态
    private String csrf;    //防跨站请求伪造
    private String accessDeniedPage;    //拒绝访问提示链接
    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
    public String getSuccessForwardUrl() {
        return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
        this.successForwardUrl = successForwardUrl;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getPermitAll() {
        return permitAll;
    }

    public void setPermitAll(String permitAll) {
        this.permitAll = permitAll;
    }

    public String getLogOut() {
        return logOut;
    }

    public void setLogOut(String logOut) {
        this.logOut = logOut;
    }

    public String getLogOutSuccessUrl() {
        return logOutSuccessUrl;
    }

    public void setLogOutSuccessUrl(String logOutSuccessUrl) {
        this.logOutSuccessUrl = logOutSuccessUrl;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getCsrf() {
        return csrf;
    }

    public void setCsrf(String csrf) {
        this.csrf = csrf;
    }

    public String getAccessDeniedPage() {
        return accessDeniedPage;
    }

    public void setAccessDeniedPage(String accessDeniedPage) {
        this.accessDeniedPage = accessDeniedPage;
    }
}
