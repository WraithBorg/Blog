package com.zxu.demo.java8.classsics_demo.vo;

public class BaseEntityVO {
    protected String id;
    protected String name;
    protected String pinYin;
    protected String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseEntityVO() {
    }

    protected BaseEntityVO(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseEntityDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pinYin='" + pinYin + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
