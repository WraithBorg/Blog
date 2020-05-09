package com.zxu.demo.java8;

import java.math.BigDecimal;

public class StaticTest{
    public static void main(String[] args){

    }
}

class A{
    public BigDecimal money;
    public String rmb;
    private String showMoney;
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public String getRmb() {
        return rmb;
    }

    public void setRmb(String rmb) {
        this.rmb = rmb;
    }

    public String getShowMoney() {
        return money.toString() + rmb;
    }

    public void setShowMoney(String showMoney) {
        this.showMoney = money.toString() + rmb;
    }
}
