package com.zxu.demo.test;

import com.zxu.demo.java8.jwt.JwtUtil;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("id", "a11111");
        map.put("name", "张");

        String token = JwtUtil.sign(map, 60L * 1000L * 30L);
        System.out.println(token);

    }
}
