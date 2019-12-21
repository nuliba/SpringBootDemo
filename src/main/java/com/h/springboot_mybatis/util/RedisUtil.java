package com.h.springboot_mybatis.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    RedisTemplate<String,String> redisTemplate;


    public <T> T get(String key,Class<T> type){
        String jsonData = redisTemplate.opsForValue().get(key);
        return JSON.parseObject(jsonData,type);
    }

    public <T> List<T> getArray(String key,Class<T> type){
        String jsonData = redisTemplate.opsForValue().get(key);
        return JSON.parseArray(jsonData,type);
    }

    public void set(String key,Long time,Object objData){
        String jsonData = JSON.toJSONString(objData);
        redisTemplate.opsForValue().set(key,jsonData,time, TimeUnit.SECONDS);
    }

    public void del(String key){
        redisTemplate.delete(key);
    }

    public boolean hasKey (String key){
        return redisTemplate.hasKey(key);
    }
}
