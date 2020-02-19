# 分析查询语句
```sql
# 打开profiling
set profiling=on;
```

```sql
show profiles;
----------------------+
|        1 | 0.607382 | select * from employees                                                                                                                                                                     |
|        2 | 3.277181 | SELECT
	emp.emp_no,
	sa.salary
FROM
	employees emp
INNER JOIN titles t
LEFT JOIN salaries sa ON sa.emp_no = emp.emp_no
WHERE
	emp.emp_no = t.emp_no
AND t.from_date BETWEEN '2000-01-01'
AND '2008-01-01' |
+----------+----------+

```

```sql
MySQL为每一个操作生成了一个Query_ID，
可以使用show profile for query 2  来查看第七条语句具体的执行过程分析
+----------------------+----------+
| Status               | Duration(持续时间) | 
+----------------------+----------+
| starting             | 9E-5     |
| checking permissions(权限) | 3E-6     |
| checking permissions | 2E-6     |
| checking permissions | 3E-6     |
| Opening tables       | 0.001731 |
| init                 | 2.6E-5   |
| System lock          | 8E-6     |
| optimizing           | 1E-5     |
| statistics           | 2.7E-5   |
| preparing            | 1.4E-5   |
| executing            | 2E-6     |
| Sending data         | 3.275218 |
| end                  | 7E-6     |
| query end            | 6E-6     |
| closing tables       | 6E-6     |
| freeing items        | 2E-5     |
| cleaning up          | 1.1E-5   |
+----------------------+----------+
```
```sql
# 将科学计数法转换成小数
mysql> SELECT CAST(9E-5 AS DECIMAL(19,6)) RES;
+----------+
| RES      |
+----------+
| 0.000090 |
+----------+
```
```
System lock	确认是由于哪个锁引起的，通常是因为MySQL或InnoDB内核级的锁引起的建议：如果耗时较大再关注即可，一般情况下都还好
Sending data	从server端发送数据到客户端，也有可能是接收存储引擎层返回的数据，再发送给客户端，数据量很大时尤其经常能看见，备注：Sending Data不是网络发送，是从硬盘读取，发送到网络是Writing to net。建议：通过索引或加上LIMIT，减少需要扫描并且发送给客户端的数据量
Sorting result	正在对结果进行排序，类似Creating sort index，不过是正常表，而不是在内存表中进行排序建议：创建适当的索引
Table lock	表级锁，没什么好说的，要么是因为MyISAM引擎表级锁，要么是其他情况显式锁表
create sort index	当前的SELECT中需要用到临时表在进行ORDER BY排序。建议：创建适当的索引
checking query cache for querychecking privileges on cachedsending cached result to clienstoring result in query cache	和query cache相关的状态-------------经多次强烈建议关闭
```