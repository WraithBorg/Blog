#### 根据指定顺序排序
```
SELECT
	emp_no
FROM
	employees
WHERE
	emp_no IN (
		'10001',
		'10002',
		'10004',
		'10005',
		'10003'
	)
ORDER BY
	FIND_IN_SET(
		emp_no,
		'10001,10003,10005,10002,10004'
	) ASC;
```

#### IN类型子查询优化
```
-- CREATE TABLE t_1(t_1_id INT UNIQUE ,t_1_col_1 INT,t_1_col_2 VARCHAR(10));
-- CREATE TABLE t_2(t_2_id INT UNIQUE ,t_2_col_1 INT,t_2_col_2 VARCHAR(10));
-- INSERT INTO t_1 VALUES (1,11,'t_1_1');
-- INSERT INTO t_1 VALUES (2,12,NULL);
-- INSERT INTO t_1 VALUES (3,NULL,'t_1_3');
-- INSERT INTO t_1 VALUES (4,14,'t_1_4');
-- INSERT INTO t_1 VALUES (5,15,NULL);
-- INSERT INTO t_1 VALUES (7,NULL,NULL);

-- INSERT INTO t_2 VALUES (1,11,'t_2_1');
-- INSERT INTO t_2 VALUES (2,NULL,'t_2_2');
-- INSERT INTO t_2 VALUES (3,13,NULL);
-- INSERT INTO t_2 VALUES (4,14,'t_2_4');
-- INSERT INTO t_2 VALUES (6,16,'t_2_6');
-- INSERT INTO t_2 VALUES (7,NULL,NULL);
```
1. SQL1
```
SELECT
	t_1.*
FROM
	t_1
WHERE
	t_1_id IN (SELECT t_2_id FROM t_2);

```
2. SQL2 与SQL1等效
```
SELECT
	t1.*
FROM
	t_1 t1
LEFT JOIN t_2 t2 ON t1.t_1_id = t2.t_2_id
WHERE
	t2.t_2_id IS NOT NULL;
```

3. SQL1和SQL2 查询计划一样,但是SQL2效率更高
```
查询优化器对子查询进行了优化，通过子查询上拉技术，将子查询转化为连接条件实现hash半连接操作
```
4.
```
SELECT t_1.* FROM t_1 WHERE EXISTS (SELECT t_2_id FROM t_2 WHERE t_1_id = 1) AND t_1_id = 2;
结果永远为null,因为 t_1_id = 1) AND t_1_id = 2
```

#### IN 和 OR 选择
```
使用到IN查询时，都是按照如下方式处理的:
[1] 对IN列表中的数值进行排序。
[2] 对于查询的匹配，每次使用二分查找去匹配IN列表的数值。
所以对于第[2]步，每次比较的算法复杂度大概为O(log n)
相反，对于同样逻辑的OR列表，每次都要遍历，
所以OR相应的算法复杂度为O(n)(因此对于遍历非常大的OR列表，会很缓慢)。
```

# 天数日期转换
SELECT TO_DAYS('2020-02-18'); -- 737838
SELECT FROM_DAYS('737838'); -- 2020-02-18

# 为什么别名不能在group by中用？ by中用
```$xslt
因为SQL的执行顺序为：
先where 再group 再having 再select 后order.

sql语句解析的顺序的问题。先where条件过滤出需要的纪录，再对筛选出来的记录分组group加having。接下来就是选取字段的过滤select最后order排序。所以别名只有在select和order by内才可以只用。
————————————————
版权声明：本文为CSDN博主「从心所愿」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/sanbingyutuoniao123/article/details/53523267
```
```
# 保留两位小数  结果=2.10
select concat(cast(2.100 as decimal(18,2)),"美元");

# 保留四位小数  结果=2.1000
select concat(cast(2.100 as decimal(18,4)),"美元");

# 保留N位小数 ,但是末尾不要0，结果=2.1
select concat(0+CAST(2.100 as char),"美元");

# 保留两位小数，四舍五入去, 结果=1.00美元
SELECT CONCAT(ROUND(1.00000,2),"美元");

# 保留两位小数，四舍五入并去掉多余的0,结果是1美元
select concat(0+CAST(ROUND(1.00000,2) as char),"美元");
```
