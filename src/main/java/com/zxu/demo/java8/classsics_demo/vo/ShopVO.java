package com.zxu.demo.java8.classsics_demo.vo;

public class ShopVO extends BaseEntityVO {
    public ShopVO(String id, String name, String code) {
        super(id, name, code);
    }

    @Override
    public String toString() {
        return "ShopDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pinYin='" + pinYin + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
