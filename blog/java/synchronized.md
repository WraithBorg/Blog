# synchronized关键字

[实战Java高并发程序设计P66]()

---

### 1、synchronized不能和**引用对象会发生改变的变量** 使用
1. 错误示例 
``` java
public class TestOther implements Runnable {
    private static Integer i = new Integer(0);
    private static TestOther other = new TestOther();
    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            synchronized (i) {
                i++;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(other);
        Thread t2 = new Thread(other);
        Thread t3 = new Thread(other);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        // 输出结果 1083075，期望结果 3000000
        System.out.println(i);
    }
}
```

2. 线程池常见问题
``` java
//  inited 错误使用
private sattic Boolean inited=false;
public ThreadPoolTaskExecutor taskExecutor(){
		return pool;
}
/**
 * 初始线程池
 */
public void init(){
	synchronized(inited){
		if(!inited){
			pool = new ThreadPoolTaskExecutor();
			if(corepoolsize!=null) pool.setCorePoolSize(corepoolsize);
			if(maxpoolsize!=null) pool.setMaxPoolSize(maxpoolsize);
			if(wait!=null) pool.setWaitForTasksToCompleteOnShutdown(wait);
			if(queue!=null) pool.setQueueCapacity(queue);
			if(alive!=null) pool.setKeepAliveSeconds(alive);
			pool.initialize();
			inited=true;
		}
	}
}
```
