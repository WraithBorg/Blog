package com.zxu.demo.java8.classsics_demo;


import com.zxu.demo.java8.classsics_demo.convert.BillConverter;
import com.zxu.demo.java8.classsics_demo.convert.NewBillConverter;
import com.zxu.demo.java8.classsics_demo.domain.StoreBill;
import com.zxu.demo.java8.classsics_demo.service.StoreBillService;
import com.zxu.demo.java8.classsics_demo.utils.HsmBFuncUtils;
import com.zxu.demo.java8.classsics_demo.vo.StoreBillVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class StoreBillController {

    @Resource
    private static StoreBillService storeBillService = new StoreBillService();
    @Resource
    private static BillConverter billConverter  = new BillConverter();
    @Resource
    private static NewBillConverter newBillConverter  = new NewBillConverter();

    /**
     * 查询单据信息
     */
    @RequestMapping("getBill")
    public static StoreBillVO getBill(String billId) {
        // 查询数据
        StoreBill bill = storeBillService.get(billId, new String[]{"billType", "getOrgan", "sendOrgan"});
        // 转换DTO
        StoreBillVO billDTO = billConverter.from(bill);
        System.out.println(billDTO);
        return billDTO;
    }
    /**
     * 查询单据信息 新写法
     */
    @RequestMapping("NEW_getBill")
    public static StoreBillVO NEW_getBill(String billId) {
        // 设定装配那些属性
        HsmBFuncUtils installProps = new HsmBFuncUtils().billType().sendOrgan().getOrgan();
        // 查询数据
        StoreBill bill = storeBillService.get(billId, installProps.props());
        // 转换DTO
        StoreBillVO billDTO = newBillConverter.form(bill,installProps);
        System.out.println(billDTO);
        return billDTO;
    }

    public static void main(String[] args) {
        getBill("String billId");
        NEW_getBill("String billId");
    }
}
