package com.example.share.string;

public class D {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        testStr();
        System.out.println(String.valueOf(System.currentTimeMillis() - startTime));
    }

    static void testStr() {//
        for (int i = 0; i < 1000000; i++) {
            String str1 = "abc" + "qwe" + "zxc";
        }
    }

    static void testSB() {//
        for (int i = 0; i < 1000000; i++) {
            String str2 = new StringBuilder("abc").append("qwe").append("zxc").toString();
        }
    }

}
