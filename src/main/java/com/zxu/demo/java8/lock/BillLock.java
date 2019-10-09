package com.zxu.demo.java8.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BillLock  implements AutoCloseable {
    public static AtomicInteger integer = new AtomicInteger(0);
    public static ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap();

    private static final Logger logger = LoggerFactory.getLogger(BillLock.class);
    private List<String> lockedBillIds = new ArrayList<>();
    public BillLock() {
    }
    public BillLock(String... billIds) {
        this.lock(billIds);
    }

    /**
     * 单据上锁
     *
     * @param billIds 单据id
     */
    public void lock(String... billIds) {
        if (billIds != null && billIds.length != 0) {
            for (String billId : billIds) {
//                logger.error("单据加锁：{}", billId);
                if (RedisCache.put(billId, "") == null) {
                    lockedBillIds.add(billId);
                } else {
                    throw new RuntimeException(RedisCache.get(billId) + " 正在操作该单据，请重新打开单据!");
                }
            }
        }
    }

    @Override
    public void close() {
        if (lockedBillIds != null && lockedBillIds.size() > 0) {
            for (String billId : lockedBillIds) {
//                logger.error("单据解锁：{}", billId);
                RedisCache.remove(billId);
            }
        }
    }

}
