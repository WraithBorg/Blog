package com.zxu.demo.java8;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 10000000; i++) {
            map.put(i + "", new Object());

        }
    }
}

