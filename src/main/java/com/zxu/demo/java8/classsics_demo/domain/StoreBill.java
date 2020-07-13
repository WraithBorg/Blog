package com.zxu.demo.java8.classsics_demo.domain;

import java.math.BigDecimal;
import java.util.Date;

public class StoreBill {
    private String id;
    private Date arrivalDate;
    private Date busDate;
    private String remark;
    private Integer billState;// 1：待提交；2：待审核； 3：已审核
    private String handNo;
    private BillBusType billBusType;
    private Organ getOrgan;
    private Organ outStore;
    private Organ sendOrgan;
    private BigDecimal sellIncludeTaxSumMoney;
    private BigDecimal sellSumMoney;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getBusDate() {
        return busDate;
    }

    public void setBusDate(Date busDate) {
        this.busDate = busDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHandNo() {
        return handNo;
    }

    public void setHandNo(String handNo) {
        this.handNo = handNo;
    }

    public BillBusType getBillBusType() {
        return billBusType;
    }

    public void setBillBusType(BillBusType billBusType) {
        this.billBusType = billBusType;
    }

    public Organ getGetOrgan() {
        return getOrgan;
    }

    public void setGetOrgan(Organ getOrgan) {
        this.getOrgan = getOrgan;
    }

    public Organ getOutStore() {
        return outStore;
    }

    public void setOutStore(Organ outStore) {
        this.outStore = outStore;
    }

    public Organ getSendOrgan() {
        return sendOrgan;
    }

    public void setSendOrgan(Organ sendOrgan) {
        this.sendOrgan = sendOrgan;
    }

    public BigDecimal getSellIncludeTaxSumMoney() {
        return sellIncludeTaxSumMoney;
    }

    public void setSellIncludeTaxSumMoney(BigDecimal sellIncludeTaxSumMoney) {
        this.sellIncludeTaxSumMoney = sellIncludeTaxSumMoney;
    }

    public BigDecimal getSellSumMoney() {
        return sellSumMoney;
    }

    public void setSellSumMoney(BigDecimal sellSumMoney) {
        this.sellSumMoney = sellSumMoney;
    }

    public Integer getBillState() {
        return billState;
    }

    public void setBillState(Integer billState) {
        this.billState = billState;
    }

    @Override
    public String toString() {
        return "StoreBill{" +
                "arrivalDate=" + arrivalDate +
                ", busDate=" + busDate +
                ", remark='" + remark + '\'' +
                ", handNo='" + handNo + '\'' +
                ", billBusType=" + billBusType +
                ", getOrgan=" + getOrgan +
                ", outStore=" + outStore +
                ", sendOrgan=" + sendOrgan +
                ", id='" + id + '\'' +
                ", sellIncludeTaxSumMoney=" + sellIncludeTaxSumMoney +
                ", sellSumMoney=" + sellSumMoney +
                '}';
    }
}
