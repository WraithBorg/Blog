package com.zxu.demo.java8.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestOther implements Runnable {
    private static TestOther instance = new TestOther();
    private static ConcurrentHashMap<String, AtomicInteger> conMap ;
    private static List<String> billList = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(BillLock.class);

    @Override
    public void run() {
        for (String id :conMap.keySet()) {
            AtomicInteger integer = conMap.get(id);
            int oriInt = integer.get() + 1;
            try (BillLock billLock = new BillLock(id)) {
                integer.addAndGet(1);
//                Thread.sleep(200);
                if (integer.get() != oriInt){
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

    public TestOther() {
        conMap = new ConcurrentHashMap<>();
        conMap.put("40288d7c6c84cf1b016c8569f8fe007b",new AtomicInteger(1));
        conMap.put("40288d7c6c84cf1b016c856a4114007e",new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898eda190003",new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898f0e9b0005",new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898f30e10007",new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898f6a110009",new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898f9b42000b",new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898fcc67000d",new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c898feaab000f",new AtomicInteger(1));
        conMap.put("40288d7c6c898a28016c899348ea0038",new AtomicInteger(1));
        conMap.put("40288d7c6c8e6cc5016c8ead3d880037",new AtomicInteger(1));
        conMap.put("40288d7c6c900507016c9009da7a0029",new AtomicInteger(1));
        conMap.put("40288d7c6c936366016c93d72851008d",new AtomicInteger(1));
        conMap.put("40288d7c6cd1fb6d016cd2636529003b",new AtomicInteger(1));
        conMap.put("ff8080816c18c4c8016c846d6cf40fcf",new AtomicInteger(1));
        conMap.put("ff8080816c8470ae016c847a04ba0027",new AtomicInteger(1));
        conMap.put("40288d7c6c84cf1b016c8569d3c40079",new AtomicInteger(1));
    }
}