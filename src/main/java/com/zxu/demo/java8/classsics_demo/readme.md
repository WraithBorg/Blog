# 记录一次根据开闭原则的代码优化
### 业务场景
> A,B两系统对接，严格按照A系统数据结构传递数据  
因为订单类型不同,所以不要不同的转换器,为了避免重复开发,所以多个类型的单据,用同一个转换器(com.zxu.demo.java8.classsics_demo.convert.BillConverter),  
但是会造成转换器里面if else分支过多,造成代码冗余,每次新增单据类型都会添加额外if else分支,  
因此违反开闭原则,所以据此做出优化com.zxu.demo.java8.classsics_demo.convert.NewBillConverter  

### 为什么不使用传统策略模式
1:传统策略模式无法保证对象/方法的复用
2:传统策略模式会创建越来越多的策略文件
