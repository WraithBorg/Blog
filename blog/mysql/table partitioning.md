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

#### 定义
* 水平分区：不同行的记录分配到不同的物理文件中
* 垂直分区：不同列的记录分配到不同的物理文件中
* 全局分区：数据存放在各个分区，但是所有数据索引放在一个对象中
* mysql 不支持垂直分区和全局分区
```
show variables like '%partition%'\G     -- 查看当前数据库是否启用分区功能
show plugins \G     -- 可查询是否启用分区
```
#### 作用
分区主要用于数据库高可用性管理，并非提高sql语句性能

#### 类型
* RANGE 分区
* LIST 分区
* HASH 分区
* KEY 分区

#### 查询各个分区具体信息
```
create table t(id int) engine = innodb
partition by range(id)(
	partition p0 values less than (10),
	partition p1 values less than (20)
);

select * from information_schema.PARTITIONS where table_schema=database() and table_name = 't'\G;

*************************** 1. row ***************************
                TABLE_CATALOG: def
                 TABLE_SCHEMA: newDB
                   TABLE_NAME: t
               PARTITION_NAME: p0
            SUBPARTITION_NAME: NULL
   PARTITION_ORDINAL_POSITION: 1
SUBPARTITION_ORDINAL_POSITION: NULL
             PARTITION_METHOD: RANGE
          SUBPARTITION_METHOD: NULL
         PARTITION_EXPRESSION: id
      SUBPARTITION_EXPRESSION: NULL
        PARTITION_DESCRIPTION: 10
                   TABLE_ROWS: 2
               AVG_ROW_LENGTH: 8192
                  DATA_LENGTH: 16384
              MAX_DATA_LENGTH: NULL
                 INDEX_LENGTH: 0
                    DATA_FREE: 0
                  CREATE_TIME: 2019-04-24 09:05:18
                  UPDATE_TIME: 2019-04-24 09:06:26
                   CHECK_TIME: NULL
                     CHECKSUM: NULL
            PARTITION_COMMENT:
                    NODEGROUP: default
              TABLESPACE_NAME: NULL
*************************** 2. row ***************************
                TABLE_CATALOG: def
                 TABLE_SCHEMA: newDB
                   TABLE_NAME: t
               PARTITION_NAME: p1
            SUBPARTITION_NAME: NULL
   PARTITION_ORDINAL_POSITION: 2
SUBPARTITION_ORDINAL_POSITION: NULL
             PARTITION_METHOD: RANGE
          SUBPARTITION_METHOD: NULL
         PARTITION_EXPRESSION: id
      SUBPARTITION_EXPRESSION: NULL
        PARTITION_DESCRIPTION: 20
                   TABLE_ROWS: 1
               AVG_ROW_LENGTH: 16384
                  DATA_LENGTH: 16384
              MAX_DATA_LENGTH: NULL
                 INDEX_LENGTH: 0
                    DATA_FREE: 0
                  CREATE_TIME: 2019-04-24 09:05:18
                  UPDATE_TIME: 2019-04-24 09:06:26
                   CHECK_TIME: NULL
                     CHECKSUM: NULL
            PARTITION_COMMENT:
                    NODEGROUP: default
              TABLESPACE_NAME: NULL

table_rows 分区内记录数
partition_method 分区类型

```

#### 按年份分区
```
create table sales (money int unsigned not null,createDate datetime) engine=innodb
partition by range(year(createDate))(
partition p2018 values less than (2018)
);
```
#### Command
```
alter table t drop partition p0; --删除表分区
```

#### tips
插入数据到不存在的分区 会抛异常
当一个表没有任何唯一索引的话，任何列都能做分区列，而且该列可以不唯一
如果表中有唯一索引，分区别必须是其中一个唯一索引

#### 数据库应用分类
```
OLTP（在线事务处理）
e.g.    Bolg，电子商务，网络游戏
OLAP（在线分析处理）
e.g.    数据仓库，数据集市
```

#### 为什么使用表分区
```
删除大量数据 使用表分区速度更快，如果不用表分区，容易影响业务
转储数据，才用表空间置换的方式，速度更快
OLAP应用经常会用到group by /count ,分库分表不合适
对于OLAP应用，分区能提高查询性能，因为OLAP应用大多数查询需要频繁的扫描一张表，分区后只需扫描相应分区即可。
对于OLTP应用，这种应用下，通常不会获取一张大表中10%的数据，大部分通过索引返回几条记录即可。
根据B+树索引的原理，对于一张大表，一般的B+树需要2到3次的磁盘IO，因此B+树能很好的完成工作，不需要分区的帮助。
**注意**
分区表设计不好 容易带来额外开销。
如果有1000W行数据，对主键做10个hash分区，每个分区约有100W条数据
select * from table where pk = @pk; 查询速度会更快
### 但是有另外一种情况；
100W和1000W行的数据本身构成的B+树的层次都是一样的，可能都是2层
这样，走上面主键分区的索引 并不会提高多少性能，因为IO都是两次
#### 如果1000W的B+树高度高度是3,100W的B+树 高度是2，那么按上述主键分区索引 可以避免一次IO，从而提高查询效率
#### 但是(注：5.7以上版本，对分区进行优化，该问题已解决)
如果这张表只有主键索引，没有其他索引，还有类似sql语句，select * from table where key =@key;
这是，对于key的查询需要扫描十个分区，即便每个分区查询开销为两次IO,则一共需要20次IO,而原来的单表设计，对于key的查询只需要2到3次IO
```












