package com.zxu.design.singleton;

/**
 * 线程安全单例模式
 * 优点：没有锁，并发表现好
 * 缺点：singleton 创建时间不受控制
 */
public class SafeSingleton {
    private SafeSingleton() {
    }
    private static SafeSingleton singleton = new SafeSingleton();

    public static SafeSingleton getSingleton() {
        return singleton;
    }
}
