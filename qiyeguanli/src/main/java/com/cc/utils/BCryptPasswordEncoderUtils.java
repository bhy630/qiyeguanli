package com.cc.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //对密码进行加密处理
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String s = encodePassword("admin");
        System.out.println(s
        );
    }
}
