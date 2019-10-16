package com.zxu.demo.test;

import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {
        ServiceLoader<DogService> loaders = ServiceLoader.load(DogService.class);
        for (DogService d : loaders) {
            d.sleep();
        }

    }
}
