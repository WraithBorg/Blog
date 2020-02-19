# limit
#=### 分页查询优化
> storebill_test 一百万条数据

#### 传统分页

```sql
# 0.04s 0.1s 0.03s
SELECT
	*
FROM
	storebill_test
LIMIT 1000,
 500;
```
``` sql
# 6.6s 6.4s 6.6s 
SELECT
	*
FROM
	storebill_test
LIMIT 620000,
 500;
```

#### 新分页
```
# 0.9s 0.6s 0.6s
SELECT
	*
FROM
	storebill_test dt
INNER JOIN (
	SELECT
		id
	FROM
		storebill_test
	LIMIT 620000,
	500
) t USING (id)
```

#### 总结
limit 偏秱量赹大则赹慢