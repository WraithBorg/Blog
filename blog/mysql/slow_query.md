## SQL 慢查询

#### mysql配置，启用慢查询日志
slow_query_log 设置为ON
long_query_time 记录超过特定时间的SQL
slow_query_log_file 记录慢查询日志文件名
log_queries_not_using_indexes 记录所有未使用索引的SQL,无论sql执行快慢
```text
windows my.ini
linux my.cnf
```
#### 查看慢SQL
```sql
-- 慢SQL日志是否启用
show variables LIKE '%slow_query_log%';
-- 查看慢于多少秒的SQL会记录到日志文件里
show variables LIKE '%long_query_time%';
https://www.cnblogs.com/qmfsun/p/4844472.html
```