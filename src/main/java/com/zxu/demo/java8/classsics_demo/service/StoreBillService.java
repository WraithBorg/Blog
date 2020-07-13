package com.zxu.demo.java8.classsics_demo.service;


import com.zxu.demo.java8.classsics_demo.domain.BillBusType;
import com.zxu.demo.java8.classsics_demo.domain.Organ;
import com.zxu.demo.java8.classsics_demo.domain.StoreBill;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StoreBillService {
    public static StoreBill get(String id, String[] strings) {
//        StoreBill bill = hsmBillService.getOneBill(billId, new String[]{"billType", "getOrgan", "sendOrgan"});
        BillBusType billBusType = new BillBusType();
        billBusType.setId("0430_0020_0100");
        billBusType.setName("统配出库单");
        Organ getOrgan = new Organ();
        getOrgan.setId("001");
        getOrgan.setName("喜家德西青店");
        Organ sendOrgan = new Organ();
        sendOrgan.setId("002");
        sendOrgan.setName("喜家德天津配送中心");
        //
        StoreBill storeBill = new StoreBill();
        storeBill.setGetOrgan(getOrgan);
        storeBill.setSendOrgan(sendOrgan);
        storeBill.setBillBusType(billBusType);
        storeBill.setBillState(1);
        storeBill.setBusDate(new Date());
        return storeBill;
    }
}
