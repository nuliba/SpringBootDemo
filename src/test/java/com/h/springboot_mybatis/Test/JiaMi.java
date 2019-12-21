package com.h.springboot_mybatis.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JiaMi {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bCryptPasswordEncoder.encode("123123");
        System.out.println(hashPass);

        System.out.println(bCryptPasswordEncoder.matches("123123","$2a$10$Mke10W7sT7k2ctOs5dXBwu.mKlvy7yjYhyOk6.rblGlHFvvxKPVb6"));
    }
}
