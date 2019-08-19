# data_free
> 表碎片
### 查询表碎片
方式一
``` sql
SELECT
	table_schema,
	table_name,
	data_free / 1024 / 1024 AS data_free_MB
FROM
	information_schema. TABLES
WHERE
	ENGINE LIKE 'InnoDB'
AND TABLE_NAME = 'syslog'
AND TABLE_SCHEMA = 'test';
```
方式二
``` sql
-- Data_free 字段
SHOW TABLE STATUS IN test WHERE name = 'syslog';
```