package com.zxu.demo.java8.classsics_demo.vo;


public class StoreBillVO extends BaseEntityVO {
    private String workDate;
    private String billStateFlag;// // 0：待提交 ,1：已审核  2：待审核(之前没有待审核状态，估待审核状态为2)
    private String billStateFlagName;
    private String handNO;
    private ShopVO rdcDTO;
    private ShopVO shopDTO;
    private ShopVO inShopDTO;
    private BusinessTypeVO billBusType;

    public String getHandNO() {
        return handNO;
    }

    public void setHandNO(String handNO) {
        this.handNO = handNO;
    }

    public ShopVO getRdcDTO() {
        return rdcDTO;
    }

    public void setRdcDTO(ShopVO rdcDTO) {
        this.rdcDTO = rdcDTO;
    }

    public ShopVO getShopDTO() {
        return shopDTO;
    }

    public void setShopDTO(ShopVO shopDTO) {
        this.shopDTO = shopDTO;
    }

    public ShopVO getInShopDTO() {
        return inShopDTO;
    }

    public void setInShopDTO(ShopVO inShopDTO) {
        this.inShopDTO = inShopDTO;
    }

    public BusinessTypeVO getBillBusType() {
        return billBusType;
    }

    public void setBillBusType(BusinessTypeVO billBusType) {
        this.billBusType = billBusType;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getBillStateFlag() {
        return billStateFlag;
    }

    public void setBillStateFlag(String billStateFlag) {
        this.billStateFlag = billStateFlag;
    }

    public String getBillStateFlagName() {
        return billStateFlagName;
    }

    public void setBillStateFlagName(String billStateFlagName) {
        this.billStateFlagName = billStateFlagName;
    }

    @Override
    public String toString() {
        return "StoreBillVO{" +
                "workDate='" + workDate + '\'' +
                ", billStateFlag='" + billStateFlag + '\'' +
                ", billStateFlagName='" + billStateFlagName + '\'' +
                ", handNO='" + handNO + '\'' +
                ", rdcDTO=" + rdcDTO +
                ", shopDTO=" + shopDTO +
                ", inShopDTO=" + inShopDTO +
                ", billBusType=" + billBusType +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pinYin='" + pinYin + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
