### 表分区

#### 使用表分区
将分区/子分区 数据与 另一个非分区表数据进行交换
```
//如果非分区表中的数据为空，那么相当于将分区中的数据移动到非分区表中，若分区表中的数据为空，则相当于将外部表中的数据导入到分区中，即，哪边不为空，哪边就是被移出的，哪边为空，哪边就是装数据的
要使用alter table…exchange partition语句，必须满足下面的条件：

A：要交换的表需要和分区表有着完全相同的表结构，但是要交换的表不能含有分区
B：在非分区表中的数据必须在交换的分区定义内（即要对上分区列的定义范围）
C：被交换的表中不能含有外键，或者其他的表含有对该表的外键引用
D：用户除了需要alter,insert,create权限外，还需要drop权限，此外，还有两个小的细节需要注意：
a：使用该语句时，不会触发交换表和被交换表上的触发器
b：auto_increment列将被重置
```
#### 操作表分区
```
//  创建分区表
CREATE TABLE e(id INT NOT NULL,fname VARCHAR(30),lname VARCHAR(30)) PARTITION BY RANGE(id)(
PARTITION p0 VALUES LESS THAN(50),PARTITION p1 VALUES LESS THAN(100),
PARTITION p2 VALUES LESS THAN(150),PARTITION p3 VALUES LESS THAN MAXVALUE);
-- 插入数据
INSERT INTO e VALUES(1669,'JIM','smith'),(337,'mary','jones'),(16,'frank','withe'),(2005,'linda','black');
//  创表非分区表
CREATE TABLE e2 LIKE e;
ALTER TABLE e2 REMOVE PARTITIONING;

// 查询表分区
mysql> SELECT partition_name,table_rows FROM information_schema.partitions WHERE table_name='e';
+----------------+------------+
| partition_name | table_rows |
+----------------+------------+
| P0             |          1 |
| P1             |          0 |
| P2             |          0 |
| P3             |          3 |
+----------------+------------+

// 将e表分区p0 数据 移动到 e2中
ALTER TABLE e EXCHANGE PARTITION p0 WITH TABLE e2;

// 查询各分区数据
mysql> SELECT partition_name,table_rows FROM information_schema.partitions WHERE table_name='e';
+----------------+------------+
| partition_name | table_rows |
+----------------+------------+
| p0             |          0 |
| p1             |          0 |
| p2             |          0 |
| p3             |          3 |
+----------------+------------+
// 查询e2表
mysql> SELECT * FROM e2;
+----+-------+-------+
| id | fname | lname |
+----+-------+-------+
| 16 | frank | withe |
+----+-------+-------+

// p0分区中的数据已经被转移到了e2表中，再次执行交换语句：
ALTER TABLE e EXCHANGE PARTITION p0 WITH TABLE e2;

// 查询e2表
mysql> SELECT * FROM e2;
Empty set (0.00 sec)
mysql> SELECT * FROM e;
+------+-------+-------+
| id   | fname | lname |
+------+-------+-------+
|   16 | frank | withe |
| 1669 | JIM   | smith |
|  337 | mary  | jones |
| 2005 | linda | black |
+------+-------+-------+

发现表e2数据又被转移到了分区表e的p0分区中，
注意，再把数据转回去的时候，可能select partition_name,table_rows from information_schema.partitions where table_name='e';
这句无法实时查出在分区中行数的变化。
```








