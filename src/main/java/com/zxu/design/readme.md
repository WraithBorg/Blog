java虚拟机
设计模式
高并发
spring cloud 源码

设计模式：使用现有的解决方案处理不断出现的问题 避免重复的工作
三要素；环境，问题，解决方案
核心思想；进行设计的复用

# 单例模式：确保一个类有唯一的一个实例
有些对象我们只需要一个：线程池，缓存，对话框，注册表，日志对象
减少了重复创建对象的过程，尤其是重量级对象

*Spring bean默认作用域是Singleton，但是可以添加注解修改作用域*
+ prototype 每次请求都会创建一个bean实例
+ request 每个http请求创建一个bean，该bean仅在当前Http request内有效
+ session 每次http请求创建一个bean，该bean仅在当前Http session内有效
+ global-session 全局session作用域，spring5 已经放弃

*Spring实现单例方式*
```
<bean id = "userService" class="com.service.UserService" scope="singleton" />
```
spring 通过ConcurrentHashMap实现单例注册表的方式 实现bean的单例模式


*线程安全的单例模式*
```
// 线程安全的单例模式
public class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton() {
    }
    public static Singleton getInstance() {
        return instance;
    }
}
```


# spring 用到的设计模式
### 1.	控制反转 IOC 和依赖注入 DI
IOC Inversion of Controller,一种解耦的设计思想
Spring IOC 容器类似工厂一样，需要创建一个对象时，只需要修改配置文件或注解即可，完全不用考虑对象是如何创建出来的
IOC容器负责创建对象，比如@Autowired Service 
a需要b对象的时候，a必须去创建b对象，但是当系统引入IOC容器后，可以指定IOC容器去创建一个b对象注入到对象a中，
a获得依赖对象b的过程由主动变成了被动，控制权翻转

DI Dependency Inject 依赖注入，是实现控制反转的一种设计模式，依赖注入就是将实例变量传入到一个对象中去

### 工厂设计模式
spring 可以通过BeanFactory和ApplicationContext创建bean对象
两者比较
+ BeanFactory : 延迟注入，使用到某个bean的时候才会注入
+ ApplicationContext： 容器启动的时候创建所有bean

*ApplicationContext三个实现类*
+ ClassPathXmlApplication: 把上下文文件当成类路径资源
+ FileSystemXmlApplication：从文件系统的XML文件载入上下文定义信息
+ XmlWebApplicationContext: 从Web系统中的XML文件载入上下文定义的信息
+ ApplicationContext context = new FileSystemXmlApplicationContext("c://project/spring.xml");

### 代理模式
`常用在AOP中 Aspect Oriented Programming`将与业务无关却被很多业务模块调用的逻辑封装起来，减少重复代码和模块间的耦合度，利于扩展,比如 事务处理，日志管理，权限控制。
Spring AOP基于动态代理，有JDK代理和CGLib代理
Spring AOP和AspectJ AOP,spring aop属于运行时增强，AspectJ属于编译时增强，Spring AOP基于代理，AspectJ基于字节码操作


### 策略模式
*优点*
+ 完美支持开闭原则
+ 避免多重条件转移语句
+ 最核心原则是依赖倒转原则
*适用情况*
+ 如果一个系统里面的许多类，他们的区别仅在于他们的行为，那个可以使用策略模式，让一个对象在许多行为中选择一种行为
+ 一个系统需要动态的在几种算法中选择一种
+ 不希望客户端知道算法相关的数据接口，使用策略模式，在具体策略类里面封装算法和相关数据结构，提高算法保密性和安全性

### 观察者模式 又叫发布订阅模式,是一种对象行为模式
当被观察者发生改变的时候，观察者会观察到相应的变化并且做出响应
观察不是直接调用
常用形式:注册-通知-撤销注册


### 模版方法模式
模版方法是一种行为设计模式，他定义了一个操作中的算法骨架，并将一些步骤延迟到了子类中，
模版方法使子类不可改变算法结构，但是可以重新定义该算法的某些特定步骤
`Spring中 jdbcTemplate 和 hibernateTemplate等以Template结尾的对数据库操作的类就用到了模版模式`
一般情况使用继承的方式实现模版方式，但是spring使用Callback模式和模版方法结合，即达到了代码复用的效果，同时增加了灵活性

### 




### 面向对象设计原则
单一职责原则 : 单一功能原则，一个类只会因为一个原因而变化
开闭原则 ； 对扩展开放，对修改关闭
里氏代换原则: 任何基类出现的地方，子类一定可以出现，使用基类的地方一定适用于其子类，而且察觉不出父类对象和子类对象的区别
接口隔离原则 ： 客户端不要依赖他不需要的接口，即将一些大的接口细化成小的接口供客户端使用
迪米特法则 ：一个实体尽可能少的与其他实体发生相互作用
依赖倒转原则 ；程序要依赖于抽象接口，而不是具体实现，即针对接口编程，不要针对实现编程
合成复用原则；要求复用时尽量使用对象组合而不是使用继承




