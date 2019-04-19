**PERFORMANCE_SCHEMA 主要用于手机数据库服务器性能参数，并且库里的表存储引擎均为PERFORMANCE_SCHEMA**
### 查看session占用内存
```
SELECT
	SUBSTRING_INDEX(SUBSTRING_INDEX(event_name, '/', 2), '/' ,- 1 ) AS event_type,
	ROUND(SUM( current_number_of_bytes_used ) / 1024 / 1024, 2 ) AS MB_currently_used
FROM
	PERFORMANCE_SCHEMA .memory_summary_global_by_event_name
GROUP BY
	event_type
HAVING
	MB_currently_used > 0
```

### 查看哪个sql执行最多
```
SELECT
	SCHEMA_NAME,
	DIGEST_TEXT,
	COUNT_STAR,
	SUM_ROWS_SENT,
	SUM_ROWS_EXAMINED,
	FIRST_SEEN,
	LAST_SEEN
FROM
	events_statements_summary_by_digest
ORDER BY
	COUNT_STAR DESC
LIMIT 1
// 返回结果1
SCHEMA_NAME	DIGEST_TEXT	COUNT_STAR	SUM_ROWS_SENT	SUM_ROWS_EXAMINED	FIRST_SEEN	         LAST_SEEN
  null       null        168828813	638289567	    64878294184	        2018-08-28 13:41:22  2019-04-19 10:38:57
// 返回结果2
*************************** 1. row ***************************
      SCHEMA_NAME: employees
      DIGEST_TEXT: INSERT INTO `salaries` VALUES (...) /* , ... */
       COUNT_STAR: 228
    SUM_ROWS_SENT: 0
SUM_ROWS_EXAMINED: 0
       FIRST_SEEN: 2019-04-03 08:33:21
        LAST_SEEN: 2019-04-03 08:36:45

```