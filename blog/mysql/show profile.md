# show profile
#### enable
```
查询是否开始，profiling
mysql> show variables like '%pro%';
+------------------------------------------+-------+
| Variable_name                            | Value |
+------------------------------------------+-------+
| check_proxy_users                        | OFF   |
| have_profiling                           | YES   |
| mysql_native_password_proxy_users        | OFF   |
| performance_schema_max_program_instances | -1    |
| profiling                                | OFF   |
| profiling_history_size                   | 15    |
| protocol_version                         | 10    |
| proxy_user                               |       |
| sha256_password_proxy_users              | OFF   |
| slave_compressed_protocol                | OFF   |
| stored_program_cache                     | 256   |
+------------------------------------------+-------+
// 开启
set profiling = 1;
// 帮助
help profiling;

```
注意
```
mysql> show profiles;
+----------+------------+--------------------------------+
| Query_ID | Duration   | Query                          |
+----------+------------+--------------------------------+
|        1 | 0.02272925 | help 'profiling'               |
|        2 | 0.00454700 | help 'profile'                 |
|        3 | 0.00057850 | SELECT DATABASE()              |
|        4 | 0.00082450 | show databases                 |
|        5 | 0.00040200 | show tables                    |
|        6 | 0.05750325 | select count(*) from employees |
|        7 | 0.00045950 | show warnnings                 |
|        8 | 0.00050550 | show warnings                  |
+----------+------------+--------------------------------+

mysql> show warnings;
+---------+------+--------------------------------------------------------------------------------------------------------------+
| Level   | Code | Message                                                                                                      |
+---------+------+--------------------------------------------------------------------------------------------------------------+
| Warning | 1287 | 'SHOW PROFILES' is deprecated and will be removed in a future release. Please use Performance Schema instead |
+---------+------+--------------------------------------------------------------------------------------------------------------+

```
```
mysql> show tables from performance_schema;
```


