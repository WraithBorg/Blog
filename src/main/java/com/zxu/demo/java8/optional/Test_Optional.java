package com.zxu.demo.java8.optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Test_Optional {
    private static final Logger logger = LoggerFactory.getLogger(Test_Optional.class);

    public static void main(String[] args) {
        t_orElse();
    }

    /* ORELSE*/
    public static void t_orElse() {
        OUser zx = getUser(1).orElse(new OUser(1, "zxorElse"));
        OUser zx1 = getUser(1).orElseGet(() -> new OUser(1, "zxorElseGet"));
//        OUser zx2 = getUser(1).orElseThrow(() ->new RuntimeException("自定义异常"));
        OUser zx3 = getUser(1).orElseThrow(() -> new OTestExceptoin(""));
//        System.out.println(zx.toString());
    }

    public static Optional<OUser> getUser(int id) {
        return Optional.empty();
//        return Optional.of(new OUser(1, "zxorempty"));
    }
}
