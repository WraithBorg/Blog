# SPI机制
`SPI的全名为Service Provider Interface`
```java
public interface DogService {
	void sleep();
}
public class WhilteDogServiceImpl implements DogService {
	@Override
	public void sleep() {
		System.out.println("白色dog。。。呼呼大睡觉...");
	}
}
public class Test {
    public static void main(String[] args) {
        ServiceLoader<DogService> loaders = ServiceLoader.load(DogService.class);
        for (DogService d : loaders) {
            d.sleep();
        }
    }
}
```
```java
新建文件 src\resourcces\META-INF\services

```