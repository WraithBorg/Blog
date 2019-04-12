# information_schema
```
mysql> use information_schema;
select concat(round(sum(DATA_LENGTH/1024/1024), 2), 'MB') as data from TABLES; -- 查看所有数据大小
select concat(round(sum(DATA_LENGTH/1024/1024), 2), 'MB') as data from TABLES where table_schema='z0211'; -- 看看数据库
select concat(round(sum(DATA_LENGTH/1024/1024),2),'MB') as data from TABLES where table_schema='z0211'  and table_name='orderbill'; -- 查看表大小
```

### TABLES
```
mysql> SELECT * FROM information_schema.`TABLES` WHERE TABLE_SCHEMA = 'z0211' AND TABLE_NAME = 'syslog';
+---------------+--------------+--------------+------------+--------+---------+------------+------------+----------------+-------------+-----------------+--------------+-----------+----------------+---------------------+-------------+------------+-----------------+----------+----------------+---------------+
| TABLE_CATALOG | TABLE_SCHEMA | TABLE_NAME   | TABLE_TYPE | ENGINE | VERSION | ROW_FORMAT | TABLE_ROWS | AVG_ROW_LENGTH | DATA_LENGTH | MAX_DATA_LENGTH | INDEX_LENGTH | DATA_FREE | AUTO_INCREMENT | CREATE_TIME         | UPDATE_TIME | CHECK_TIME | TABLE_COLLATION | CHECKSUM | CREATE_OPTIONS | TABLE_COMMENT |
+---------------+--------------+--------------+------------+--------+---------+------------+------------+----------------+-------------+-----------------+--------------+-----------+----------------+---------------------+-------------+------------+-----------------+----------+----------------+---------------+
| def           | zzz          | syslog       | BASE TABLE | InnoDB |      10 | Dynamic    |      10711 |           1032 |    11059200 |               0 |     14548992 |   2097152 |           NULL | 2019-04-12 15:23:00 | NULL        | NULL       | utf8_general_ci |     NULL |                | 系统日志      |
+---------------+--------------+--------------+------------+--------+---------+------------+------------+----------------+-------------+-----------------+--------------+-----------+----------------+---------------------+-------------+------------+-----------------+----------+----------------+---------------+
| 数据表登记目录| 数据库名     | 表名称       | 表类型     | 引擎   | 版本    | 行格式：   | 多少行数据 |  平均行长度    |    数据长度 |  最大数据长度   |    索引长度  |  空间碎片 | 自动增量当前值 | 表的创建时间        | 表的更新时间|表的检查时间|表字符校验编码集 |   校验和 | 创建选项       | 表的注释、备注|
+---------------+--------------+--------------+------------+--------+---------+------------+------------+----------------+-------------+-----------------+--------------+-----------+----------------+---------------------+-------------+------------+-----------------+----------+----------------+---------------+

```
#### 详细说明
Table_type 表类型：[system view|base table]
Version	版本：默认值10
Row_format 行格式：[Compact|Dynamic|Fixed]
	不存在varchar text bolb可变长度字段的表为静态表，row_format是fixed,
		优点：读取速度快 缺点：浪费额外空间
	存在varchar text bolb可变长度字段的表为动态表，row_format为dynamic
		有点：节省空间，缺点：读取慢
	还有其他值：DEFAULT COMPACT(紧凑)
	修改行格式
		ALTER TABLE table_name ROW_FORMAT = DEFAULT
		修改 fixed --> dynamic 结果char 变成varchar
		修改 dynamic --> fixed 结果varchar 变成char
Auto_increment：做自增主键的自动增量当前值

data_free
	每当MySQL从你的列表中删除了一行内容，该段空间就会被留空。而在一段时间内的大量删除操作，会使这种留空的空间变得比存储列表内容所使用的空间更大。
	当MySQL对数据进行扫描时，它扫描的对象实际是列表的容量需求上限，也就是数据被写入的区域中处于峰值位置的部分。如果进行新的插入操作，MySQL将尝试利用这些留空的区域，但仍然无法将其彻底占用。
	1. 查询数据库空间碎片
	mysql> SELECT table_name,data_free,engine FROM information_schema.`TABLES` WHERE TABLE_SCHEMA = 'zzz' AND TABLE_NAME = 'xt_log';
	+------------+-----------+--------+
	| table_name | data_free | engine |
	+------------+-----------+--------+
	| xt_log     |   4194304 | InnoDB |
	+------------+-----------+--------+
	2.对数据表优化：
	optimeze table `table_name`;

#### 磁盘空间碎片回收

	碎片空间回收利含三层含义：
		数据库中已有的数据被删除（delete）后，对于这些数据原有的碎片空间，查询（select）数据时是否会被扫描
		再次添加（insert）数据时，碎片空间是否会被重复利用
		如何物理地回收这些碎片空间，以减小存储压力，回收碎片对系统有何影响






















