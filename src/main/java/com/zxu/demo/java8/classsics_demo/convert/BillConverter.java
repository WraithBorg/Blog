package com.zxu.demo.java8.classsics_demo.convert;

import com.zxu.demo.java8.classsics_demo.domain.StoreBill;
import com.zxu.demo.java8.classsics_demo.utils.DDateUtil;
import com.zxu.demo.java8.classsics_demo.vo.BusinessTypeVO;
import com.zxu.demo.java8.classsics_demo.vo.ShopVO;
import com.zxu.demo.java8.classsics_demo.vo.StoreBillVO;
import org.springframework.stereotype.Component;

@Component
public class BillConverter {
    /**
     * 将单据 StoreBill 转换成 StoreBillVO(前端展示数据结构)
     * // 1:不符合 开放封闭原则，当程序需要改变时，应该通过拓展的方式来实现变化，而不是通过修改已有代码来实现
     * // 2:随着单据类型 和 条件分支的增加，会变得更冗余，方法体会更长
     */
    public static StoreBillVO from(StoreBill billDB) {
        StoreBillVO billDTO = new StoreBillVO();
        //      单据类型
        BusinessTypeVO businessTypeDTO = null;
        if (billDB.getBillBusType() != null) {
            businessTypeDTO = new BusinessTypeVO();
            businessTypeDTO.setId(billDB.getBillBusType().getId());
            businessTypeDTO.setName(billDB.getBillBusType().getName());
        }
        //      ## 发货机构
        ShopVO rdcDTO = null;
        if (billDB.getSendOrgan() != null) {
            if (billDB.getSendOrgan() != null) {
                rdcDTO = new ShopVO(billDB.getSendOrgan().getId(), billDB.getSendOrgan().getName(), billDB.getSendOrgan().getCode());
            }
        }
        //      ## 收货机构
        ShopVO inShopDTO = null;
        if (billDB.getGetOrgan() != null) {
            if (billDB.getGetOrgan() != null) {
                inShopDTO = new ShopVO(billDB.getGetOrgan().getId(), billDB.getGetOrgan().getName(), billDB.getGetOrgan().getCode());
            }
        }
        //      ## 单据所属机构  根据单据类型取不同的值

        billDTO.setInShopDTO(inShopDTO);
        billDTO.setBillBusType(businessTypeDTO);
        billDTO.setRdcDTO(rdcDTO);
//     TODO 设置通用属性
//        //      ## 出库金额总额
//        billDTO.setSumOutMoneyWithTax(billDB.getSellIncludeTaxSumMoney());
//        billDTO.setSumOutMoney(billDB.getSellSumMoney());
//        //      ## 生成方式 0 手工新建，1 分派生成，2 差异生成，3 统配生成，4 MRP生成，5：食堂接口导入生成
//        billDTO.setId(billDB.getId());
//        billDTO.setArriveDate(billDB.getArrivalDate());
//        billDTO.setWeightState(billDB.getWeighState());// 是否一致
//        billDTO.setBillMemo(billDB.getRemark());
//        billDTO.setDetailCount(billDB.getBillDtCount());
//        billDTO.setManualCode(billDB.getHandNo());
//        billDTO.setSysbillCode(billDB.getBillNo());
//        billDTO.setDifference(false);// 默认无差异
//        billDTO.setSupplierDTO(null);//暂时不支持供货商
//        billDTO.setBillDate(billDB.getMakeDate());
//        billDTO.setCheckDate(billDB.getAuditDate());
//        billDTO.setFatherId(billDB.getParentBill());
        if (billDB.getBillState() == 1) {//1：待提交；2：待审核； 3：已审核
            billDTO.setBillStateFlag("0");
            billDTO.setBillStateFlagName("待提交");
        } else if (billDB.getBillState() == 2) {
            billDTO.setBillStateFlag("2");
            billDTO.setBillStateFlagName("待审核");
        } else {
            billDTO.setBillStateFlag("1");
            billDTO.setBillStateFlagName("已审核");
        }

        billDTO.setWorkDate(DDateUtil.formYMdHms(billDB.getBusDate()));

        return billDTO;
    }


}
