package com.zxu.demo.java8.lock;

import java.util.concurrent.ConcurrentHashMap;

public class RedisCache {
    private static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
    static String put(String key,String value){
        return concurrentHashMap.putIfAbsent(key,value);
    }
    static void remove(String key){
         concurrentHashMap.remove(key);
    }
    static String get(String key){
        return concurrentHashMap.get(key);
    }

}
