# EXPLAIN
```
mysql> explain select * from salaries where salary in (50000,6000) order by from_date limit 10;
+----+-------------+-------+------------+--------+---------------+---------+---------+---------------------+--------+----------+-----------------------------+
| id | select_type | table | partitions | type   | possible_keys | key     | key_len | ref                 | rows   | filtered | Extra                       |
+----+-------------+-------+------------+--------+---------------+---------+---------+---------------------+--------+----------+-----------------------------+
|  1 | SIMPLE      | sa    | p07,p08    | ALL    | PRIMARY       | NULL    | NULL    | NULL                | 283936 |     2.22 | Using where; Using filesort |
|  1 | SIMPLE      | emp   | NULL       | eq_ref | PRIMARY       | PRIMARY | 4       | employees.sa.emp_no |      1 |   100.00 | NULL                        |
+----+-------------+-------+------------+--------+---------------+---------+---------+---------------------+--------+----------+-----------------------------+

```
#### select_type
```
select 查询类型: SIMPLE PRIMARY UNION SUBQUERY
SIMPLE
    简单查询，不包括连接查询和子查询
PRIMARY
    主查询 或 最外层查询
UNION
    连接查询的第二个或后面的SELECT语句
DEPENDENT UNION
    连接查询的第二个或后面的SELECT语句，取决于最外面查询
UNION RESULT
    连接查询的结果
SUBQUERY
    子查询中第一个SELECT语句
DEPENDENT SUBQUERY
    子查询中第一个SELECT语句，取决于最外面查询
DERIVED
    导出表的SELECT（FROM子句的子查询）
```
#### table
```
表名
```
#### partitions
```
表分区
```
#### type
```
连接类型：本数据表与其他数据表之间的关系，最佳类型到最差类型排序：system,const,eq_ref,ref,range,index,all
system
    仅有一行的系统表
const
    数据表最多只有一个匹配行，将在查询开始时读取，并在余下查询中最为常量存在。
    const查询速度快，因为只读取一次
    mysql> explain select * from employees where emp_no = '284158';
    +----+-------------+-----------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
    | id | select_type | table     | partitions | type  | possible_keys | key     | key_len | ref   | rows | filtered | Extra |
    +----+-------------+-----------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
    |  1 | SIMPLE      | employees | NULL       | const | PRIMARY       | PRIMARY | 4       | const |    1 |   100.00 | NULL  |
    +----+-------------+-----------+------------+-------+---------------+---------+---------+-------+------+----------+-------+
eq_ref
    对于来自前面表的行数据，只能在当前表找到一行数据。当使用索引并且索引是unique或primary key的情况，常用于 "=" 操作符
    mysql> explain select * from salaries sa left join employees emp on sa.emp_no = emp.emp_no limit 10;
    +----+-------------+-------+------------+--------+---------------+---------+---------+---------------------+---------+----------+-------+
    | id | select_type | table | partitions | type   | possible_keys | key     | key_len | ref                 | rows    | filtered | Extra |
    +----+-------------+-------+------------+--------+---------------+---------+---------+---------------------+---------+----------+-------+
    |  1 | SIMPLE      | sa    | NULL       | ALL    | NULL          | NULL    | NULL    | NULL                | 2625474 |   100.00 | NULL  |
    |  1 | SIMPLE      | emp   | NULL       | eq_ref | PRIMARY       | PRIMARY | 4       | employees.sa.emp_no |       1 |   100.00 | NULL  |
    +----+-------------+-------+------------+--------+---------------+---------+---------+---------------------+---------+----------+-------+

ref
    对于来自前面表的行数据，能从当前表中找到所有匹配的行，用于索引既不是unique也不是 primary key的情况，或者查询中使用了索引列的左边部分列的组合
    ref可以用于 使用<=> 操作符的带索引的列
    mysql> explain select * from employees emp left join salaries sa on sa.emp_no = emp.emp_no limit 10;
    +----+-------------+-------+------------+------+---------------+---------+---------+----------------------+--------+----------+-------+
    | id | select_type | table | partitions | type | possible_keys | key     | key_len | ref                  | rows   | filtered | Extra |
    +----+-------------+-------+------------+------+---------------+---------+---------+----------------------+--------+----------+-------+
    |  1 | SIMPLE      | emp   | NULL       | ALL  | NULL          | NULL    | NULL    | NULL                 | 299290 |   100.00 | NULL  |
    |  1 | SIMPLE      | sa    | NULL       | ref  | PRIMARY       | PRIMARY | 4       | employees.emp.emp_no |      9 |   100.00 | NULL  |
    +----+-------------+-------+------------+------+---------------+---------+---------+----------------------+--------+----------+-------+
    mysql> explain select * from salaries where emp_no = '284158';
    +----+-------------+----------+------------+------+---------------+---------+---------+-------+------+----------+-------+
    | id | select_type | table    | partitions | type | possible_keys | key     | key_len | ref   | rows | filtered | Extra |
    +----+-------------+----------+------------+------+---------------+---------+---------+-------+------+----------+-------+
    |  1 | SIMPLE      | salaries | NULL       | ref  | PRIMARY       | PRIMARY | 4       | const |   14 |   100.00 | NULL  |
    +----+-------------+----------+------------+------+---------------+---------+---------+-------+------+----------+-------+
ref_or_null
    连接类型同ref，但是包括所有null值的行，解决子查询中经常使用该连接类型的优化
range
    使用索引检索给定范围的行，key表示使用哪列索引，key_len包含使用索引的最长关键元素
    当使用 =，<，>，>=，<=，is null，<=>，between，in时，类型为range
    mysql> explain select * from employees where emp_no > 11000 and emp_no < 12000;
    +----+-------------+-----------+------------+-------+---------------+---------+---------+------+------+----------+-------------+
    | id | select_type | table     | partitions | type  | possible_keys | key     | key_len | ref  | rows | filtered | Extra       |
    +----+-------------+-----------+------------+-------+---------------+---------+---------+------+------+----------+-------------+
    |  1 | SIMPLE      | employees | NULL       | range | PRIMARY       | PRIMARY | 4       | NULL |  999 |   100.00 | Using where |
    +----+-------------+-----------+------------+-------+---------------+---------+---------+------+------+----------+-------------+
index
    只扫描索引树，另外一种形式的全表扫描，连接类型同ALL,通常比All快，因为索引文件通常比数据文件小
    mysql> explain select * from salaries order by emp_no limit 10000,1;
    +----+-------------+----------+------------+-------+---------------+---------+---------+------+-------+----------+-------+
    | id | select_type | table    | partitions | type  | possible_keys | key     | key_len | ref  | rows  | filtered | Extra |
    +----+-------------+----------+------------+-------+---------------+---------+---------+------+-------+----------+-------+
    |  1 | SIMPLE      | salaries | NULL       | index | NULL          | PRIMARY | 7       | NULL | 10001 |   100.00 | NULL  |
    +----+-------------+----------+------------+-------+---------------+---------+---------+------+-------+----------+-------+
all
    全表扫描，如果表是第一个没标记const的表，非常不好，可以增加更多索引来避免all连接
    e.g. 即时日期createDate字段为索引，如果日期查询范围涵盖整个表，那个type也等于ALL
    mysql> explain select * from salaries order by from_date limit 10000,1;
    +----+-------------+----------+------------+------+---------------+------+---------+------+---------+----------+----------------+
    | id | select_type | table    | partitions | type | possible_keys | key  | key_len | ref  | rows    | filtered | Extra          |
    +----+-------------+----------+------------+------+---------------+------+---------+------+---------+----------+----------------+
    |  1 | SIMPLE      | salaries | NULL       | ALL  | NULL          | NULL | NULL    | NULL | 2625474 |   100.00 | Using filesort |
    +----+-------------+----------+------------+------+---------------+------+---------+------+---------+----------+----------------+
```
#### possible_keys
```
可选的索引，为null则没有用到索引
```
#### key
```
实际用到的索引，可以强制使用或者提供选择索引 force index,use index,ignore index
```
#### key_ken
```
索引按字节计算的长度，key_len数值越小，查询越快
```
#### ref
```
关联关系中 另一个表中数据列的名字
表示使用哪个列或常数与索引一起来查询

```
#### row
```
查询时必须检查的行数
```
#### extra
```
处理查询的详细信息
```



#### tips
```
大表和小表关联 哪个做主表？
    大表做主表的话，扫描行数少，但是连接类型一般为 ref
    小表做主表，扫描行数多，但是连接类型一般为 eq_ref
    至于哪个适合做主表，需要看具体查询计划决定
```











