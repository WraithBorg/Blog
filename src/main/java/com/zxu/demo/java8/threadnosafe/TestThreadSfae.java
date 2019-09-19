package com.zxu.demo.java8.threadnosafe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestThreadSfae implements Runnable {
    private static final Map<String, String> testMap = new HashMap<>();
    private static TestThreadSfae instance = new TestThreadSfae();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            testMap.put(String.valueOf(i), String.valueOf(System.currentTimeMillis()));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        Thread t3 = new Thread(instance);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        System.out.println(testMap.size());
        Set<String> set = new HashSet<>();
        for (String key : testMap.keySet()) {
            if (set.contains(key)) {
                System.out.println(key + "重复");
            } else {
                set.add(key);
            }
        }
        System.out.println(set.size());
    }


}
