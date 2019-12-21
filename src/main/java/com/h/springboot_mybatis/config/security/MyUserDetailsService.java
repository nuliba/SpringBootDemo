package com.h.springboot_mybatis.config.security;

import com.h.springboot_mybatis.mapper.CarMapper;
import com.h.springboot_mybatis.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名查询账号信息
        Car car = carMapper.queryByUserName(s);
        if(car != null){
            //设置账号角色
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
            //组装用户账号信息并返回
            UserDetails userDetails = new User(car.getCarName(),car.getPassword(),authorityList);
            return userDetails;
        }
        return null;
    }
}
