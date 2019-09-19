package com.zxu.demo.test;

import com.zxu.demo.java8.lock.BillLock;

public class Test {
    public static void main(String[] args) {
        try (BillLock lock = new BillLock("111111","2222","33333")) {
            System.out.printf("1");
            if (true) {
                throw new RuntimeException("测试");
            }
            System.out.printf("111");
        }

    }
}
