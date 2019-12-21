package com.h.springboot_mybatis.controller;

import com.h.springboot_mybatis.pojo.Car;
import com.h.springboot_mybatis.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping("/showData")
    @ResponseBody
    public String showData(){
        List<Car> list = carService.queryAll();
        for (Car car: list) {
            System.out.println(car.toString());
        }
        return  list.toString();
    }

    @RequestMapping("/login")
    public String login(){
        return "redirect:../public/login.html";
    }

    @RequestMapping(value = "/error")
    public String error(){
        System.out.println("error");
        return "/error/error";
    }
}