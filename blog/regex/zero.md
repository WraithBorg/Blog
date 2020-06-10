#### 删除所有多余空行
```
# notepad
查找目标 \n[\s| ]*\r
替换为
```
####  查找没有规定小数精度的divide方法，避免小数除不尽的bug	(发现两处)	 
```
// Regex divide\(.+\..+\(\)\)  

× tPrice=sdt.x().divide(sdt.x()).setScale(4, BigDecimal.ROUND_HALF_UP);
√ tPrice=sdt.x().divide(sdt.x(), 4, BigDecimal.ROUND_HALF_UP));
 
```
#### 查找有精度损失的BigDecimal	 (发现三处)
```
// Regex  BigDecimal\([0-9]+\.

System.out.println(new BigDecimal(0.01));
// 输出结果 0.01000000000000000020816681711721685132943093776702880859375

× a.x(x.x().multiply(new BigDecimal(0.01)));
√ a.x(x.x().multiply(new BigDecimal("0.01")));
```

#### finally里不要写return
```
// Regex finally.*\{\n.*return

```

