package com.example.share.string;

public class E {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        testSS();
        System.out.println(String.valueOf(System.currentTimeMillis() - startTime));
    }

    static void testStr() {// 745 702
        String str = "";
        for (int i = 0; i < 20000; i++) {
            str += "abcde";
        }
        System.out.println(str);
    }

    static void testSB() {// 7 10
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            builder.append("abcde");
        }
        System.out.println(builder.toString());
    }

    static void testSS() {// 711 713
        String str = "";
        for (int i = 0; i < 20000; i++) {
            str += new StringBuilder("abcde");
        }
        System.out.println(str);
    }
}
