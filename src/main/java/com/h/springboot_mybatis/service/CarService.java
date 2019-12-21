package com.h.springboot_mybatis.service;

import com.h.springboot_mybatis.pojo.Car;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CarService{
    public List<Car> queryAll();

    public Car queryByUserName(String username);
}
