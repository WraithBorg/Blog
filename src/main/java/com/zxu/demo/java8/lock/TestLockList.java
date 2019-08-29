package com.zxu.demo.java8.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLockList implements Runnable {
    private static TestLockList instance = new TestLockList();
    private static ConcurrentHashMap<String, AtomicInteger> conMap;
    private static List<String> billList = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(BillLock.class);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

            AtomicInteger _01 = conMap.get("40288d7c6c84cf1b016c8569d3c40079");
            AtomicInteger _02 =conMap.get("40288d7c6c84cf1b016c8569f8fe007b");
            AtomicInteger _03 = conMap.get("40288d7c6c84cf1b016c856a4114007e");
            AtomicInteger _04 =conMap.get("40288d7c6c898a28016c898eda190003");
            AtomicInteger _05 =conMap.get("40288d7c6c898a28016c898f0e9b0005");
            AtomicInteger _06 =conMap.get("40288d7c6c898a28016c898f30e10007");
            AtomicInteger _07 =conMap.get("40288d7c6c898a28016c898f6a110009");
            AtomicInteger _08 = conMap.get("40288d7c6c898a28016c898f9b42000b");
            AtomicInteger _09 =conMap.get("40288d7c6c898a28016c898fcc67000d");
            AtomicInteger _10 = conMap.get("40288d7c6c898a28016c898feaab000f");
            AtomicInteger _11 =conMap.get("40288d7c6c898a28016c899348ea0038");
            AtomicInteger _12 = conMap.get("40288d7c6c8e6cc5016c8ead3d880037");
            AtomicInteger _13 = conMap.get("40288d7c6c900507016c9009da7a0029");
            AtomicInteger _14 = conMap.get("40288d7c6c936366016c93d72851008d");
            AtomicInteger _15 = conMap.get("40288d7c6cd1fb6d016cd2636529003b");
            AtomicInteger _16 = conMap.get("ff8080816c18c4c8016c846d6cf40fcf");
            AtomicInteger _17 = conMap.get("ff8080816c8470ae016c847a04ba0027");

            int i_01 = _01.get() + 1;
            int i_02 = _01.get() + 1;
            int i_03 = _01.get() + 1;
            int i_04 = _01.get() + 1;
            int i_05 = _01.get() + 1;
            int i_06 = _01.get() + 1;
            int i_07 = _01.get() + 1;
            int i_08 = _01.get() + 1;
            int i_09 = _01.get() + 1;
            int i_10 = _01.get() + 1;
            int i_11 = _01.get() + 1;
            int i_12 = _01.get() + 1;
            int i_13 = _01.get() + 1;
            int i_14 = _01.get() + 1;
            int i_15 = _01.get() + 1;
            int i_16 = _01.get() + 1;
            int i_17 = _01.get() + 1;



            try (BillLock billLock = new BillLock(
                    "40288d7c6c84cf1b016c8569d3c40079",
                    "40288d7c6c84cf1b016c8569f8fe007b",
                    "40288d7c6c84cf1b016c856a4114007e",
                    "40288d7c6c898a28016c898eda190003",
                    "40288d7c6c898a28016c898f0e9b0005",
                    "40288d7c6c898a28016c898f30e10007",
                    "40288d7c6c898a28016c898f6a110009",
                    "40288d7c6c898a28016c898f9b42000b",
                    "40288d7c6c898a28016c898fcc67000d",
                    "40288d7c6c898a28016c898feaab000f",
                    "40288d7c6c898a28016c899348ea0038",
                    "40288d7c6c8e6cc5016c8ead3d880037",
                    "40288d7c6c900507016c9009da7a0029",
                    "40288d7c6c936366016c93d72851008d",
                    "40288d7c6cd1fb6d016cd2636529003b",
                    "ff8080816c18c4c8016c846d6cf40fcf",
                    "ff8080816c8470ae016c847a04ba0027")) {
                _01.addAndGet(1);
                _02.addAndGet(1);
                _03.addAndGet(1);
                _04.addAndGet(1);
                _05.addAndGet(1);
                _06.addAndGet(1);
                _07.addAndGet(1);
                _08.addAndGet(1);
                _09.addAndGet(1);
                _10.addAndGet(1);
                _11.addAndGet(1);
                _12.addAndGet(1);
                _13.addAndGet(1);
                _14.addAndGet(1);
                _15.addAndGet(1);
                _16.addAndGet(1);
                _17.addAndGet(1);

                Thread.sleep(200);

                if (_01.get() != i_01) {
                    logger.error("并发异常");
                }
                if (_02.get() != i_02) {
                    logger.error("并发异常");
                }
                if (_03.get() != i_03) {
                    logger.error("并发异常");
                }
                if (_04.get() != i_04) {
                    logger.error("并发异常");
                }
                if (_05.get() != i_05) {
                    logger.error("并发异常");
                }
                if (_06.get() != i_06) {
                    logger.error("并发异常");
                }
                if (_07.get() != i_07) {
                    logger.error("并发异常");
                }
                if (_08.get() != i_08) {
                    logger.error("并发异常");
                }
                if (_09.get() != i_09) {
                    logger.error("并发异常");
                }
                if (_10.get() != i_10) {
                    logger.error("并发异常");
                }
                if (_11.get() != i_11) {
                    logger.error("并发异常");
                }
                if (_12.get() != i_12) {
                    logger.error("并发异常");
                }
                if (_13.get() != i_13) {
                    logger.error("并发异常");
                }
                if (_14.get() != i_14) {
                    logger.error("并发异常");
                }
                if (_15.get() != i_15) {
                    logger.error("并发异常");
                }
                if (_16.get() != i_16) {
                    logger.error("并发异常");
                }
                if (_17.get() != i_17) {
                    logger.error("并发异常");
                }
            } catch (Exception e) {
            }
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
        System.out.println("测试完成");//结果：单号：DD19030816012661000
    }

    public TestLockList() {
        conMap = new ConcurrentHashMap<>();
        conMap.put("40288d7c6c84cf1b016c8569f8fe007b", new AtomicInteger(1));
        conMap.put("40288d7c6c84cf1b016c856a4114007e", new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898eda190003", new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898f0e9b0005", new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898f30e10007", new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898f6a110009", new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898f9b42000b", new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898fcc67000d", new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898feaab000f", new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c899348ea0038", new AtomicInteger(1));
        conMap.put("40288d7c6c8e6cc5016c8ead3d880037", new AtomicInteger(1));
        conMap.put("40288d7c6c900507016c9009da7a0029", new AtomicInteger(1));
        conMap.put("40288d7c6c936366016c93d72851008d", new AtomicInteger(1));
        conMap.put("40288d7c6cd1fb6d016cd2636529003b", new AtomicInteger(1));
        conMap.put("ff8080816c18c4c8016c846d6cf40fcf", new AtomicInteger(1));
        conMap.put("ff8080816c8470ae016c847a04ba0027", new AtomicInteger(1));
        conMap.put("40288d7c6c84cf1b016c8569d3c40079", new AtomicInteger(1));
    }
}
