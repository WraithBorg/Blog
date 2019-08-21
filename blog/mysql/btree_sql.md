# 基于索引类型 BTree的优化方案

#### 区分in和exists， not in和not exists
```
select * from 表A where id in (select id from 表B)
上面sql语句相当于
    select * from 表A where exists(select * from 表B where 表B.id=表A.id)
区分in和exists主要是造成了驱动顺序的改变（这是性能变化的关键）

如果是exists，那么以外层表为驱动表，先被访问，
如果是IN，那么先执行子查询。所以IN适合于外表大而内表小的情况；EXISTS适合于外表小而内表大的情况。
关于not in和not exists，推荐使用not exists，不仅仅是效率问题，not in可能存在逻辑问题。如何高效的写出一个替代not exists的sql语句？
原sql语句
    select colname … from A表 where a.id not in (select b.id from B表)
高效的sql语句
    select colname … from A表 Left join B表 on a.id = b.id where b.id is null
```

#### Common
```
SQL语句中IN包含的值不应过多
    MySQL对于IN做了相应的优化，即将IN中的常量全部存储在一个数组里面，而且这个数组是排好序的。但是如果数值较多，产生的消耗也是比较大的
    当只需要一条数据的时候，使用limit 1， 这是为了使EXPLAIN中type列达到const类型
```
```
尽量用union all代替union
    union和union all的差异主要是前者需要将结果集合并后再进行唯一性过滤操作，这就会涉及到排序，增加大量的CPU运算，加大资源消耗及延迟。当然，union all的前提条件是两个结果集没有重复数据。
```
```
不使用ORDER BY RAND()
    select id from `table_name` order by rand() limit 1000;
    上面的sql语句，可优化为
    select id from `table_name` t1 join (select rand() * (select max(id) from `table_name`) as nid) t2 ont1.id > t2.nid limit 1000;
```

```
避免在 where 子句中对字段进行 null 值判断
    对于null的判断可能会导致引擎放弃使用索引而进行全表扫描。
```
```
如果排序字段没有用到索引，就尽量少排序
```
```
不建议使用%前缀模糊查询
    例如LIKE “%name”或者LIKE “%name%”，这种查询会导致索引失效而进行全表扫描。但是可以使用LIKE “name%”。
    那么如何解决这个问题呢，答案：使用全文索引
    在我们查询中经常会用到select id,fnum,fdst from table_name where user_name like '%zhangsan%'; 。这样的语句，普通索引是无法满足查询需求的。庆幸的是在MySQL中，有全文索引来帮助我们。
    创建全文索引的sql语法是：
    ALTER TABLE `table_name` ADD FULLTEXT INDEX `idx_user_name` (`user_name`);
    使用全文索引的sql语句是：
    select id,fnum,fdst from table_name where match(user_name) against('zhangsan' in boolean mode);
    注意：在需要创建全文索引之前，请联系DBA确定能否创建。同时需要注意的是查询语句的写法与普通索引的区别
```
```    
避免在where子句中对字段进行表达式操作
    比如
    select user_id,user_project from table_name where age*2=36;
    中对字段就行了算术运算，这会造成引擎放弃使用索引，建议改成：
    select user_id,user_project from table_name where age=36/2;
```
```
对于联合索引来说，要遵守最左前缀法则
```
```
必要时可以使用force index来强制查询走某个索引
```
```
注意范围查询语句
    对于联合索引来说，如果存在范围查询，比如between,>,<等条件时，会造成后面的索引字段失效。
```
```
MySQL中没有full join，可以用以下方式来解决
    select * from A left join B on B.name = A.name  where B.name is null union all select * from B;
```
```
尽量使用inner join，避免left join
    参与联合查询的表至少为2张表，一般都存在大小之分。
    如果连接方式是inner join，在没有其他过滤条件的情况下MySQL会自动选择小表作为驱动表，
    但是left join在驱动表的选择上遵循的是左边驱动右边的原则，即left join左边的表名为驱动表。
```
```
巧用STRAIGHT_JOIN
    inner join是由mysql选择驱动表，但是有些特殊情况需要选择另个表作为驱动表，
    比如有group by、order by等「Using filesort」、「Using temporary」时
    。STRAIGHT_JOIN来强制连接顺序，在STRAIGHT_JOIN左边的表名就是驱动表，右边则是被驱动表。
    在使用STRAIGHT_JOIN有个前提条件是该查询是内连接，也就是inner join。其他链接不推荐使用STRAIGHT_JOIN，否则可能造成查询结果不准确。
```
```
find_in_set 没有用到索引，使用in

```