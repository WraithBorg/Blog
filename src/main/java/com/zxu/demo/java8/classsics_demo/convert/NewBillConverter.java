package com.zxu.demo.java8.classsics_demo.convert;


import com.zxu.demo.java8.classsics_demo.domain.StoreBill;
import com.zxu.demo.java8.classsics_demo.enm.BillStateEnum;
import com.zxu.demo.java8.classsics_demo.utils.DDateUtil;
import com.zxu.demo.java8.classsics_demo.utils.HsmBFuncUtils;
import com.zxu.demo.java8.classsics_demo.vo.StoreBillVO;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class NewBillConverter {
    /**
     * BillConverter 优化之后
     * 将单据 StoreBill 转换成 StoreBillVO (前端展示数据结构)
     */
    public static StoreBillVO form(StoreBill bill, HsmBFuncUtils hsmBFunc) {
        StoreBillVO dto = generateBaseProps(bill);
        for (BiFunction<StoreBill, StoreBillVO, StoreBillVO> fun : hsmBFunc.li()) {
            fun.apply(bill, dto);
        }
        return dto;
    }

    /**
     * 组装基本属性
     */
    private static StoreBillVO generateBaseProps(StoreBill billDB) {
        StoreBillVO billDTO = new StoreBillVO();
        billDTO.setId(billDB.getId());
//  TODO    设置通用属性
//        billDTO.setSumOutMoneyWithTax(billDB.getSellIncludeTaxSumMoney());//出库金额总额
//        billDTO.setSumOutMoney(billDB.getSellSumMoney());
//        billDTO.setManualCode(billDB.getHandNo());
//        billDTO.setSysbillCode(billDB.getBillNo());
//        billDTO.setDifference(false);// 默认无差异
//        billDTO.setSupplierDTO(null);//暂时不支持供货商
//        billDTO.setBillDate(billDB.getMakeDate());
//        billDTO.setCheckDate(billDB.getAuditDate());
//        billDTO.setFatherId(billDB.getParentBill());
        // 设置业务日期
        billDTO.setWorkDate(DDateUtil.formYMdHms(billDB.getBusDate()));
        // 设置单据状态
        BillStateEnum stateEnum = BillStateEnum.getEnm(billDB.getBillState());
        billDTO.setBillStateFlag(stateEnum.fVal());
        billDTO.setBillStateFlagName(stateEnum.fName());
        //
        return billDTO;
    }

}
