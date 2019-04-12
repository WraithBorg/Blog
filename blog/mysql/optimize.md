[TOC]
## 优化
### 索引
MySQL 索引包括 BTREE和HASH，具体和表的存储引擎相关，MyISAM和InnoDB只支持BTREE索引
> 索引优点
	1. 创建唯一索引能保证表中每一行数据的唯一性
	2. 加快查询速度
	3. 在实现数据的参考完整性方面，可以加速表和表之间的连接
	4. 使用分组和排序子句查询时，可以减少分组和排序的时间
> 索引缺点
	1. 创建和维护索引要耗费时间，随着数据量增加耗费的时间也会增加
	2. 索引会占用磁盘空间，除了数据表占数据空间之外，每一个索引还要占一定的物理空间，如果有大量索引，索引文件可能比数据文件更快达到更大尺寸；
	3. 当对表的数据进行增删改的时候，索引也要动态的维护，这样降低了数据的维护速度
> 索引维护
```
create index index_first_last on employees(first_name,last_name); -- 创建多列索引
show index from employees; -- 显示索引
alter table employees drop index index_name; -- 删除索引
create index index_first on employees(first_name); -- 创建单列索引
create index index_first_last on employees(first_name,last_name); -- 创建多列索引
```
> 索引分类
	分类 一
		1. 普通索引	允许插入重复值和空值
		2. 唯一索引 列必须唯一，但允许有空值，如果是组合索引，则列值的组合必须唯一
		3. 主键索引 特殊的唯一索引，不允许有空值
	分类 二
		1. 单列索引 
		2. 组合索引 只有查询条件使用最左边字段，索引才会被使用

#### 索引失效
	> like 关键字查询 第一个字符为 % ，索引会失效，只有%不在第一个位置 索引才会起作用
	> 使用多列索引的时候，只有使用第一个字段，索引才会生效
	> 使用or关键字，且or前后都是索引时，索引才会生效

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
