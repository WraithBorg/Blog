package com.zxu.demo.java8.lock;

import java.util.ArrayList;
import java.util.List;

public class NewLock implements AutoCloseable {
	private List<String> lockKeys = new ArrayList<>();
	public NewLock( ) {
	}
	private boolean lock(String key,String value){
		if (RedisCache.put(key, value) == null){
			lockKeys.add(key);
			return true;
		}else {
			return false;
		}
	}
	public ReturnResult lockKeys(String[] keys) {
		if (keys == null || keys.length == 0) {
			return new ReturnResult(false,"参数异常");
		}
		for (String key : keys) {
				if (!this.lock(key,SessionUtil.getCurrentUserName())){
					String userName = RedisCache.get(key);
					return  new ReturnResult(false,"用户【" + userName + "】正在操作，请5分钟后重试");
				}

		}
		return new ReturnResult(true);
	}
	@Override
	public void close() {
		if (lockKeys != null && lockKeys.size() > 0) {
			for (String billId : lockKeys) {
				RedisCache.remove(billId);
			}
		}
	}
}
