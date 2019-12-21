package com.h.springboot_mybatis.service.impl;

import com.h.springboot_mybatis.mapper.CarMapper;
import com.h.springboot_mybatis.pojo.Car;
import com.h.springboot_mybatis.service.CarService;
import com.h.springboot_mybatis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CarServiceImpl  implements CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Car> queryAll() {
        List<Car> listCar = redisUtil.getArray("queryAll",Car.class);
        if(StringUtils.isEmpty(listCar)) {
            listCar = carMapper.queryAll();
            redisUtil.set("queryAll", (long) 5,listCar);
        }
        return listCar;
    }

    @Override
    public Car queryByUserName(String username) {
        return carMapper.queryByUserName(username);
    }
}
