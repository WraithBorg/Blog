package com.zxu.demo.java8.doublecolon;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test_Double_Colon {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        list.forEach(Test_Double_Colon::printVal);
//        与上述方法等价
        Consumer<String> printVal = Test_Double_Colon::printVal;
        list.forEach(a -> printVal.accept(a));
    }

    public static void printVal(String string){
        System.out.println(string);
    }

}
