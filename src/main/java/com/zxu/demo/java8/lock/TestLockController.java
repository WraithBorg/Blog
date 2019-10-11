package com.zxu.demo.java8.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestLockController {
	private static final Logger logger = LoggerFactory.getLogger(TestLockController.class);
	/**
	 * 批量处理订单
	 */
	@RequestMapping("/batchDealBills.do")
	public ReturnResult batchDealBills(String[] billIds) {
		try (NewLock newLock = new NewLock()) {
			ReturnResult returnResult = newLock.lockKeys(billIds);
			if (!returnResult.isResult()) {
				return returnResult;
			}
		} catch (Exception e) {
			logger.error("批量审核单据:",e);
		}
		return new ReturnResult(true);
	}
}
