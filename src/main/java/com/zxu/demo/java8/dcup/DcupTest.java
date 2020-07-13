package com.zxu.demo.java8.dcup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程不安全的测试例子
 */
public class DcupTest implements Runnable {
    private static ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();

    private static DcupTest instance = new DcupTest();

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
        System.out.println("测试完成");//结果：单号：DD19030816012661000
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            int playerIndexRandomNum = Dcup.getPlayerIndexRandomNum();
            if (concurrentHashMap.containsKey(playerIndexRandomNum)) {
                System.out.println("有重复数据："+playerIndexRandomNum);
            } else {
                concurrentHashMap.put(playerIndexRandomNum, "");
            }
        }
    }
    public static class Dcup {
        private static int playerIndexPos = 0;
        private static List<Integer> playerIndexRandom = new ArrayList<>();

        static {
            for (int i = 1500000; i < 9500000; i++) {
                playerIndexRandom.add(i);
            }
            Collections.shuffle(playerIndexRandom);
        }

        public static int getPlayerIndexRandomNum() {
            Integer vipid = playerIndexRandom.get(playerIndexPos);
            playerIndexPos++;
            return vipid;
        }
    }
}
