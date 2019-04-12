# 无锁生成单号
### 生成单号测试用例
```java
/**
 * 生成单号测试用例
 */
public class TestOther implements Runnable {
	static TestOther instance = new TestOther();
	public static ConcurrentHashMap<String, String> conMap = new ConcurrentHashMap<>();

	@Override
	public void run() {
		for (int j = 0; j < 20000; j++) {
			String dd = BillNoUtil.createNo("DD");
			if (conMap.putIfAbsent(dd, "") != null) {
				System.out.println(dd);
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		Thread t3 = new Thread(instance);
		t1.start();t2.start();t3.start();
		t1.join();t2.join();t3.join();
		System.out.println("单号：" + BillNoUtil.createNo("DD"));//结果：单号：DD19030816012661000
	}
}
```
### 生成单号工具类
```java
/**
 * 生成单号工具类
 */
public class BillNoUtil {
	private static AtomicInteger orderNum = new AtomicInteger(1000);
	private static ConcurrentHashMap<String, String> conMap = new ConcurrentHashMap<>();//保存每天日期
	public static String createNo(String code) {
		// 判断是否重置序号
		String ymDay = EDITools.YDAY_FORMATT.get().format(new Date());//年月日
		if (BillNoUtil.conMap.putIfAbsent(ymDay, "") == null) {//日期改变 序号重置
			BillNoUtil.orderNum = new AtomicInteger(1000);
		}
		// 生成单号
		String increment = String.valueOf(orderNum.getAndIncrement());
		return code + EDITools.DAY_FORMATTER.get().format(new Date()) + increment;
	}
}
```
### 生成单号工具类 加锁
```java
/**
 * 生成单号工具类 加锁
 */
public class BillUtilTest {
	private static int orderNum = 1000;
	private static ConcurrentHashMap<String, String> conMap = new ConcurrentHashMap<>();//保存每天日期

	public synchronized static String createNo(String code) {
		// 判断是否重置序号
		String ymDay = EDITools.YDAY_FORMATT.get().format(new Date());//年月日
		if (BillUtilTest.conMap.putIfAbsent(ymDay, "") == null) {//日期改变 序号重置
			BillUtilTest.orderNum = 1000;
		}
		// 生成单号
		String increment = String.valueOf(orderNum++);
		return code + EDITools.DAY_FORMATTER.get().format(new Date()) + increment;
	}
}
```
*经过测试，生成 60w订单号 ，无锁方法耗时：964 960 904ms，加锁方法耗时：1292 1269 1296ms*