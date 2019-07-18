package com.zxu.demo.java8.optional;

import java.util.function.Supplier;

public class OUser implements Supplier {
    private int id;
    private String name;

    public OUser(int id, String name)  {
        this.id = id;
        this.name = name;
        System.out.println(toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public OUser get() {
        return this;
    }
}
