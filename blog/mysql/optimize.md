[TOC]
## 优化

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
ANALYZE [LOCAL | NO_WRITE_TO_BINLOG]TABLE titles;
```
mysql> ANALYZE NO_WIRITE_TO_BINLOG TABLE titles,salaries;
+--------------------+---------+----------+----------+
| Table              | Op      | Msg_type | Msg_text |
+--------------------+---------+----------+----------+
| employees.titles   | analyze | status   | OK       |
| employees.salaries | analyze | status   | OK       |
+--------------------+---------+----------+----------+
2 rows in set (0.22 sec)
```
Op: 执行操作
Msg_type: 信息类型(status(状态),info(信息),note(注意),warning,error)
Msg_text: 显示信息

### 检查表
```
mysql> CHECK TABLE titles,salaries;
+--------------------+-------+----------+----------+
| Table              | Op    | Msg_type | Msg_text |
+--------------------+-------+----------+----------+
| employees.titles   | check | status   | OK       |
| employees.salaries | check | status   | OK       |
+--------------------+-------+----------+----------+
2 rows in set (4.95 sec)
```
### 优化表
使用OPTIMIZE TABLE语句优化表，但只能优化VARCHAR BOLB TEXT 类型字段
OPTIMIZE [LOCAL | NO_WRITE_TO_BINLOG] TABLE table01,table02;
```
notice: InnoDB 表可能会执行报错
解决：
ALTER TABLE table.name ENGINE='InnoDB';  -- 转移表数据，去掉碎片直接优化，不过会影响业务
或者
在启动的时候指定--skip-new或者--safe-mode选项来支持optimize功能
然后在执行OPTIMEZE
```

#### show status
```
SHOW STATUS
    Connections 连接mysql服务器次数
    Uptime      mysql服务器上线时间
    Slow_queries 慢查询次数
    Com_select 查询操作次数
    Com_insert 插入操作次数
    Com_update 更新操作次数
    Com_delete 删除操作次数
e.g.
SHOW STATUS LIKE 'Slow_queries'
```

#### 性能瓶颈
CPU瓶颈
IOP瓶颈
网络瓶颈
用mpstat,iostat,sar,vmstat 查看系统性能状态

#### SQL设计
```
1. 不为空的一律加 not null，varchar类型都不为空，详细见第五条
2. 标记字段能用tinyint就用tinyint 别用varchar
3. int（tinyint，smallint）型字段加 unsigned
4. 能用smallint 就别用 int，能用tinyint就别用smallint
5. 备注等字段虽然业务上可以为空，但也要设置为not null，并且加上 DEFAULT ''
6. varchar 长度够用就好别太大
7. COMMENT 必须添加，表的COMMENT也要加上
8. 秉着最少占用存储的原则
```

#### MYSQL 统计指标
>* 查询吞吐量
>* 查询执行性能
>* 连接情况
>* 缓冲池使用情况
```

```
####    优化数据大小  Optimizing Data Size
* Table Columns
    尽可能使用最有效（最小）的数据类型
    Use the most efficient (smallest) data types possible. MySQL has many specialized types that save disk space and memory. For example, use the smaller integer types if possible to get smaller tables. MEDIUMINT is often a better choice than INT because a MEDIUMINT column uses 25% less space.
    尽可能声明not null, 它通过能够更好的使用索引并且减少测试是否为null的开销，除非必要，应该避免每列的值允许为空
    Declare columns to be NOT NULL if possible. It makes SQL operations faster, by enabling better use of indexes and eliminating overhead for testing whether each value is NULL. You also save some storage space, one bit per column. If you really need NULL values in your tables, use them. Just avoid the default setting that allows NULL values in every column.
Row Format
    一些字段可以使用紧凑格式
    InnoDB tables are created using the DYNAMIC row format by default

Indexes
    表的主索引应尽可能短 对于InnoDB表，主键列在每个辅助索引条目中都是重复的，因此如果您有许多辅助索引，则短主键可以节省大量空间。
    仅创建提高查询性能所需的索引。索引适用于检索，但会降低插入和更新操作的速度，
    较短的索引更快，不仅因为它们需要更少的磁盘空间，而且因为它们还会在索引缓存中为您提供更多的命中，从而减少磁盘搜索次数

Joins
    保持列名简单
Normalization
    尽量保持所有数据不冗余 根据数据库理论第三范式
    如果速度比磁盘空间更重要，并且保留多个数据副本的维护成本

#### 基于索引类型 BTree的优化方案
```
SQL语句中IN包含的值不应过多
    MySQL对于IN做了相应的优化，即将IN中的常量全部存储在一个数组里面，而且这个数组是排好序的。但是如果数值较多，产生的消耗也是比较大的
当只需要一条数据的时候，使用limit 1
    这是为了使EXPLAIN中type列达到const类型

当只需要一条数据的时候，使用limit 1
    这是为了使EXPLAIN中type列达到const类型
尽量用union all代替union
    union和union all的差异主要是前者需要将结果集合并后再进行唯一性过滤操作，这就会涉及到排序，增加大量的CPU运算，加大资源消耗及延迟。当然，union all的前提条件是两个结果集没有重复数据。
不使用ORDER BY RAND()
    select id from `table_name` order by rand() limit 1000;
    上面的sql语句，可优化为
    select id from `table_name` t1 join (select rand() * (select max(id) from `table_name`) as nid) t2 ont1.id > t2.nid limit 1000;
区分in和exists， not in和not exists

    select * from 表A where id in (select id from 表B)
    上面sql语句相当于
    select * from 表A where exists(select * from 表B where 表B.id=表A.id)

    区分in和exists主要是造成了驱动顺序的改变（这是性能变化的关键），如果是exists，那么以外层表为驱动表，先被访问，如果是IN，那么先执行子查询。所以IN适合于外表大而内表小的情况；EXISTS适合于外表小而内表大的情况。

    关于not in和not exists，推荐使用not exists，不仅仅是效率问题，not in可能存在逻辑问题。如何高效的写出一个替代not exists的sql语句？

    原sql语句

    select colname … from A表 where a.id not in (select b.id from B表)

    高效的sql语句

    select colname … from A表 Left join B表 on where a.id = b.id where b.id is null
    取出的结果集如下图表示，A表不在B表中的数据

避免在 where 子句中对字段进行 null 值判断
    对于null的判断会导致引擎放弃使用索引而进行全表扫描。
如果排序字段没有用到索引，就尽量少排序
不建议使用%前缀模糊查询
    例如LIKE “%name”或者LIKE “%name%”，这种查询会导致索引失效而进行全表扫描。但是可以使用LIKE “name%”。
    那么如何解决这个问题呢，答案：使用全文索引

    在我们查询中经常会用到select id,fnum,fdst from table_name where user_name like '%zhangsan%'; 。这样的语句，普通索引是无法满足查询需求的。庆幸的是在MySQL中，有全文索引来帮助我们。

    创建全文索引的sql语法是：
    ALTER TABLE `table_name` ADD FULLTEXT INDEX `idx_user_name` (`user_name`);

    使用全文索引的sql语句是：

    select id,fnum,fdst from table_name where match(user_name) against('zhangsan' in boolean mode);
    注意：在需要创建全文索引之前，请联系DBA确定能否创建。同时需要注意的是查询语句的写法与普通索引的区别
避免在where子句中对字段进行表达式操作
    比如
    select user_id,user_project from table_name where age*2=36;
    中对字段就行了算术运算，这会造成引擎放弃使用索引，建议改成：
    select user_id,user_project from table_name where age=36/2;
避免隐式类型转换
    where 子句中出现 column 字段的类型和传入的参数类型不一致的时候发生的类型转换，建议先确定where中的参数类型
对于联合索引来说，要遵守最左前缀法则
必要时可以使用force index来强制查询走某个索引
注意范围查询语句
    对于联合索引来说，如果存在范围查询，比如between,>,<等条件时，会造成后面的索引字段失效。
MySQL中没有full join，可以用以下方式来解决
    select * from A left join B on B.name = A.name  where B.name is null union all select * from B;
尽量使用inner join，避免left join
    参与联合查询的表至少为2张表，一般都存在大小之分。如果连接方式是inner join，在没有其他过滤条件的情况下MySQL会自动选择小表作为驱动表，但是left join在驱动表的选择上遵循的是左边驱动右边的原则，即left join左边的表名为驱动表。
巧用STRAIGHT_JOIN
    inner join是由mysql选择驱动表，但是有些特殊情况需要选择另个表作为驱动表，比如有group by、order by等「Using filesort」、「Using temporary」时。STRAIGHT_JOIN来强制连接顺序，在STRAIGHT_JOIN左边的表名就是驱动表，右边则是被驱动表。

    在使用STRAIGHT_JOIN有个前提条件是该查询是内连接，也就是inner join。其他链接不推荐使用STRAIGHT_JOIN，否则可能造成查询结果不准确。

find in set 没有用到索引，使用in

```
#### 时间复杂度/空间复杂夫
```
O(1) 
    最低时间复杂度， 耗时/耗空间，无论数据如何变化，时/耗空间都不变
    哈希算法就是典型的O(1)时间复杂度，无论数据规模多大，都可以在一次计算后找到目标
O(n)
    数据量增大几倍，耗时也增大几倍
    遍历数组
O(n^2)
    数据量增大n倍时，耗时增大n的平方倍
    冒泡排序排一个数组，对于n个变量的数组，需要交换变量位置n^2次，那么算法复杂度就是O(n^2)
O(log n)
    当数据增大n倍时，耗时增大log n倍（这里的log是以2为底的，比如，当数据增大256倍时，耗时只增大8倍，是比线性还要低的时间复杂度）
    二分查找就是O(log n)的算法，每找一次排除一半的可能，256个数据中查找只要找8次就可以找到目标
O(n log n)
    n乘以log n，当数据增大256倍时，耗时增大256*8=2048倍。这个复杂度高于线性低于平方。归并排序就是O(n log n)的时间复杂度。 
```
#### sql语句state 状态result sorting
```
TODO
如果查询计划type类型是 index_merge（索引合并排序）
· https://dev.mysql.com/doc/refman/8.0/en/index-merge-optimization.html

```
#### 分页查询优化
```sql
-- 传统分页
EXPLAIN SELECT
	*
FROM
	storebilldt
ORDER BY
	batchcode
LIMIT 12000,
 50;


-- 新分页
SELECT
	*
FROM
	storebilldt dt
INNER JOIN (
	SELECT
		id
	FROM
		storebilldt
	ORDER BY
		batchcode
	LIMIT 12000,
	50
) t USING (id)
ORDER BY
	batchcode;



```

