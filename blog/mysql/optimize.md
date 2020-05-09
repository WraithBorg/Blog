### 优化子查询
	join不需要建立临时表，所以速度比子查询快

### 优化插入记录速度
影响插入速度因素：
>* 索引，唯一性校验，一次插入记录条数
>* 一条insert 插入多条记录 比多条insert速度要快
>* load data infile 批量导入数据 比insert语句快
	
### InnoDB引擎的表 常见优化
>* 禁用唯一性检查	
`插入数据之前 执行 set unique_checks= 0;
禁止对唯一索引的检查，数据导入完成之后在运行 set unique_checks= 1;对唯一索引的检查，数据导入完成之后在运行 set unique_checks= 1;`

>* 禁用外键检查
>* 禁止自动提交

### 分析表
- [analyze table](analyze%20table.md)

### 检查表
- [check table](check%20table.md)

#### show status
- [show status](show%20status.md)

#### 性能瓶颈
CPU瓶颈
IO瓶颈
网络瓶颈
用mpstat,iostat,sar,vmstat 查看系统性能状态

#### MYSQL 统计指标
>* 查询吞吐量
>* 查询执行性能
>* 连接情况
>* 缓冲池使用情况

####    优化数据大小  Optimizing Data Size
- [optimize data](optimize%20data.md)

#### 基于索引类型 BTree的优化方案
- [btree_sql](btree_sql.md)

#### sql语句state 状态result sorting
```
如果查询计划type类型是 index_merge（索引合并排序）
· https://dev.mysql.com/doc/refman/8.0/en/index-merge-optimization.html
```
#### 避免隐式类型转换
```
TODO
```
### 分页查询优化
- [limit](limit.md)

### 表碎片整理
- [data_free](data_free.md)

### 优化表OPTIMIZE TABLE
适当执行OPTIMIZE TABLE
- [optimize table](optimize%20table.md)