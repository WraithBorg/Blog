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

