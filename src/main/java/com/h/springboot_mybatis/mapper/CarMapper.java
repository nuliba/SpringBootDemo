package com.h.springboot_mybatis.mapper;

import com.h.springboot_mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarMapper {
    public List<Car> queryAll();

    public Car queryByUserName(@Param("username") String username);
}
