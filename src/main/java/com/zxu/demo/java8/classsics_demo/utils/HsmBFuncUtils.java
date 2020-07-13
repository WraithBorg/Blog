package com.zxu.demo.java8.classsics_demo.utils;


import com.zxu.demo.java8.classsics_demo.constant.StoreBillTableColumns;
import com.zxu.demo.java8.classsics_demo.domain.StoreBill;
import com.zxu.demo.java8.classsics_demo.vo.BusinessTypeVO;
import com.zxu.demo.java8.classsics_demo.vo.ShopVO;
import com.zxu.demo.java8.classsics_demo.vo.StoreBillVO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class HsmBFuncUtils {
    private List<BiFunction<StoreBill, StoreBillVO, StoreBillVO>> li;
    private List<String> props;


    public HsmBFuncUtils() {
        this.props = new ArrayList<>();
        this.li = new ArrayList<>();
    }

    public List<BiFunction<StoreBill, StoreBillVO, StoreBillVO>> li() {
        return li;

    }

    public String[] props() {
        return props.toArray(new String[]{});
    }

    private BiFunction<StoreBill, StoreBillVO, StoreBillVO> getTillType() {
        return (v1, v2) -> {
            BusinessTypeVO businessTypeDTO = new BusinessTypeVO();
            businessTypeDTO.setId(v1.getBillBusType().getId());
            businessTypeDTO.setName(v1.getBillBusType().getName());
            v2.setBillBusType(businessTypeDTO);
            return v2;
        };
    }

    private BiFunction<StoreBill, StoreBillVO, StoreBillVO> getRdc() {
        return (v1, v2) -> {
            ShopVO rdcDTO = new ShopVO(v1.getSendOrgan().getId(), v1.getSendOrgan().getName(), v1.getSendOrgan().getCode());
            v2.setRdcDTO(rdcDTO);
            return v2;
        };
    }


    private BiFunction<StoreBill, StoreBillVO, StoreBillVO> getInShop() {
        return (v1, v2) -> {
            ShopVO inShopDTO = new ShopVO(v1.getGetOrgan().getId(), v1.getGetOrgan().getName(), v1.getGetOrgan().getCode());
            v2.setInShopDTO(inShopDTO);
            return v2;
        };
    }

    // 单据类型
    public HsmBFuncUtils billType() {
        li.add(getTillType());
        props.add(StoreBillTableColumns.billType);
        return this;
    }

    // 发货机构
    public HsmBFuncUtils sendOrgan() {
        li.add(getRdc());
        props.add(StoreBillTableColumns.sendOrgan);
        return this;
    }

    // 收货机构
    public HsmBFuncUtils getOrgan() {
        li.add(getInShop());
        props.add(StoreBillTableColumns.getOrgan);
        return this;
    }
}
