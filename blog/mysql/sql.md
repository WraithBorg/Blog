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