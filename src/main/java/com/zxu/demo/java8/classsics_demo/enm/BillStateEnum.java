package com.zxu.demo.java8.classsics_demo.enm;

public enum BillStateEnum {
    UN_CHECKED(1,"0", "待提交"),
    AUDITED(3,"1", "已审核"),
    TOBE_CHECKED(2,"2", "待审核");

    private Integer fOldVal;
    private String fVal;
    private String fName;

    BillStateEnum(Integer fOldVal, String fVal, String fName) {
        this.fOldVal = fOldVal;
        this.fVal = fVal;
        this.fName = fName;
    }

    public String fVal() {
        return fVal;
    }

    public String fName() {
        return fName;
    }

    public static BillStateEnum getEnm(Integer fOldState) {
       for (BillStateEnum e : values()){
           if (fOldState.equals(e.fOldVal)){
               return e;
           }
       }
        throw new RuntimeException("未知单据状态！");
    }


}
