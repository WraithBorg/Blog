```
public class Lock implements AutoCloseable {

    private Logger logger = LoggerFactory.getLogger(Lock.class);
	private List<String> lockedIds = new ArrayList<>();
	public Lock() {
	}
	public Lock(String... keys) {
		this.lock(keys);
	}

	/**
	 * 通用加锁方法，不一定用于单据
	 */
	public ServiceResult<?> lock(String key) {
		return this.lock(key, this.getUserName());
	}

	private ServiceResult<?> lock(String key, String value) {
		if (CommonUtil.isBlank(key)) {
			throw new NullPointerException();
		}
		if (RedisCache.put(key, value)) {
			lockedIds.add(key);
			logger.error("加锁：{}", key);
			return ServiceResult.done();
		} else {
			return ServiceResult.fail(RedisCache.get(key));
		}
	}

	/**
	 * 批量加锁
	 */
	private void lock(String... keys) {
		if (CommonUtil.isNotEmpty(keys)) {
			ServiceResult lockResult;
			for (String key : keys) {
				lockResult = this.lock(key);
				if (!lockResult.ok()) {
					throw new LockedException("【" + RedisCache.get(key) + "】正在操作，请稍后！");
				}
			}
		}
	}

	private String getUserName() {
		String userName = "其他用户";
		User currentUser = LoginUserBean.getCurrentUser();
		if (Objects.nonNull(currentUser)) {
			userName = currentUser.getName();
		}
		return userName;
	}

	@Override
	public void close() {
		if (CommonUtil.isNotEmpty(lockedIds)) {
			for (String billId : lockedIds) {
				RedisCache.remove(billId);
				logger.error("解锁：{}", billId);
			}
		}
	}
}
```