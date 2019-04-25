### DDL

```
desc table; -- 查看表结构
+--------+-------------+------+-----+---------+----------------+
| Field  | Type        | Null | Key | Default | Extra          |
+--------+-------------+------+-----+---------+----------------+
| id     | int(11)     | NO   | PRI | NULL    | auto_increment |
| name   | varchar(25) | NO   | UNI | NULL    |                |
| sex    | varchar(4)  | YES  |     | 男      |                |
| deptId | int(11)     | YES  | MUL | NULL    |                |
+--------+-------------+------+-----+---------+----------------+

MUL 表示允许出现多次
EXtra 表示附加信息
```
```
建表
Create Table: CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num1` int(11) DEFAULT NULL,
  `name` varchar(25) NOT NULL,
  `sex` varchar(4) DEFAULT '男',
  `deptId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `STH` (`name`),
  KEY `fk_emp_deptId2` (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

DROP table IF EXISTS `test`; -- 删除表
alter table test01 rename test; -- 表重命名
ALTER TABLE test MODIFY num varchar(32); -- 修改字段类型
ALTER TABLE test CHANGE num num2 int(11); -- 替换字段
ALTER TABLE test ADD num int(11); -- 添加字段
ALTER TABLE test ADD num1 int (11) AFTER deptId; -- 在 deptId 后面添加字段
ALTER TABLE test DROP num2; -- 删除列
ALTER TABLE test MODIFY num1 int(11) AFTER id; -- 修改列的排列顺序
ALTER TABLE test DROP FOREIGN KEY `fk_emp_deptId2`; -- 删除外键

删除库下面所有表
// 输出删除语句
select concat('drop table',table_name,';') from information_schema.TABLES where table_schema = 'newDB';
// shell实现批量删除
mysql -uroot -pThanos oldDB -sNe "select concat('use newDB;drop table ',table_name,';') from information_schema.TABLES where table_schema = 'newDB'" | while read table;do mysql -uroot -pThanos -sNe "$table";done
```

#### Explain
```
ALL < index < range ~ index_merge < ref < eq_ref < const < system
ALL 全表扫描
index 利用索引
range: 表示使用索引范围查询, 通过索引字段范围获取表中部分数据记录. 这个类型通常出现在 =, <>, >, >=, <, <=, IS NULL, <=>, BETWEEN, IN() 操作中
mysql >    source /test_db-master/employees.sql -- centos7 执行sql文件
+----+-------------+-----------+------------+------+---------------+------+---------+------+--------+----------+-------+
| id | select_type | table     | partitions | type | possible_keys | key  | key_len | ref  | rows   | filtered | Extra |
+----+-------------+-----------+------------+------+---------------+------+---------+------+--------+----------+-------+
|  1 | SIMPLE      | employees | NULL       | ALL  | NULL          | NULL | NULL    | NULL | 299556 |   100.00 | NULL  |
+----+-------------+-----------+------------+------+---------------+------+---------+------+--------+----------+-------+
```
#### 接着我们来查看下死锁和事务的相关指标：
```
show engine innodb status;中没有任何死锁的信息
    LATEST FOREIGN KEY ERROR  最近一次外键错误
    LATEST DETECTED DEADLOCK  最近一次死锁
information_schema.innodb_trx 、information_schema.innodb_locks 、 
information_schema.innodb_lock_waits 的也没有任何形式的锁信息。
```
#### 查看未提交事物
```
SELECT
	trx_state,
	trx_started,
	trx_mysql_thread_id,
	trx_query
FROM
	information_schema.innodb_trx
trx_state: 事务状态，一般为RUNNING 
trx_started: 事务执行的起始时间，若时间较长，则要分析该事务是否合理 
trx_mysql_thread_id: MySQL的线程ID，用于kill 
trx_query: 事务中的sql 
数据库当前thread最近一次执行的sql
SELECT * FROM performance_schema.events_statements_current
```
### mysql 表空间
查询是否启用独立表空间
show variables like 'innodb_file_per%'\G;

### 关键字
NO_WRITE_TO_BINLOG -- 执行过程不写入二进制

#### SET SQL
```
SET sql_safe_updates = 1; -- 开启安全模式，update不使用where会报错
SET FOREIGN_KEY_CHECKS = 0;     -- 禁用外键约束,作用域当前session,session重新建立连接会恢复默认值
SET FOREIGN_KEY_CHECKS = 1;     -- 启用外键约束,作用域当前session
SET GLOBAL FOREIGN_KEY_CHECKS = 0; 或 SET @@GLOBAL.FOREIGN_KEY_CHECKS = 0;  -- 禁用外键约束
```
#### SELECT SQL
```
SELECT @@FOREIGN_KEY_CHECKS;    -- 查看外键约束
select version();  -- 查看mysql版本
select database(); -- 显示正在使用的数据库
```
#### SHOW SQL
```
show create table tb_emp5; -- 显示表创建语句 show create table tb_emp5\G;
show processlist; -- 显示系统中正在运行的所有进程
show table status; -- 显示当前使用或者指定的database中的每个表的信息。信息包括表类型和表的最新更
show tables或show tables from database_name; -- 显示当前数据库中所有表的名称。
show databases; -- 显示mysql中所有数据库的名称
show columns from table_name from database_name; 或show columns from database_name.table_name; -- 显示表中列名称
show index from table_name; -- 显示表的索引。
show variables like '%storage_engine%'; -- 显示默认 存储引擎
show warnings; -- 显示最后一个执行的语句所产生的错误、警告和通知。
show errors; -- 只显示最后一个执行语句所产生的错误。
show create DATABASE test;
SHOW VARIABLES LIKE 'SQL_SAFE_UPDATES'; -- 查询mysql安全模式
```
#### Other SQL
```
H:\test_db-master>mysql -t -u root -p < employees.sql --安装数据库
```

