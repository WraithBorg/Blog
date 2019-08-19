# limit
#### 分页查询优化
``` sql
-- 传统分页
EXPLAIN SELECT
	*
FROM
	storebilldt
ORDER BY
	batchcode
LIMIT 12000,
 50;


-- 新分页
SELECT
	*
FROM
	storebilldt dt
INNER JOIN (
	SELECT
		id
	FROM
		storebilldt
	ORDER BY
		batchcode
	LIMIT 12000,
	50
) t USING (id)
ORDER BY
	batchcode;
```