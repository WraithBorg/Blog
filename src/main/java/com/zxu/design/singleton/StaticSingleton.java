package com.zxu.design.singleton;

/**
 * 线程安全单例
 * 优点：适合并发，只有第一次调动getSingleton时 才会创建单例对象，能控制单例对象创建时间
 * 缺点：
 */
public class StaticSingleton {
    private StaticSingleton() {

    }

    private static class SingletonHolder {
        private static StaticSingleton singleton = new StaticSingleton();
    }

    public static StaticSingleton getSingleton() {
        return SingletonHolder.singleton;
    }

}
