# 分析表
[Reference Document](https://dev.mysql.com/doc/refman/5.7/en/analyze-table.html)

### 分析表
ANALYZE [LOCAL | NO_WRITE_TO_BINLOG]TABLE titles;
```
mysql> ANALYZE NO_WIRITE_TO_BINLOG TABLE titles,salaries;
+--------------------+---------+----------+----------+
| Table              | Op      | Msg_type | Msg_text |
+--------------------+---------+----------+----------+
| employees.titles   | analyze | status   | OK       |
| employees.salaries | analyze | status   | OK       |
+--------------------+---------+----------+----------+
```
Op: 执行操作
Msg_type: 信息类型(status(状态),info(信息),note(注意),warning,error)

Msg_text: 显示信息

#### 
低峰期才能执行analyze table，
执行analyze table会刷新information_schema中的行计数
（select table_name,table_rows from information_schema.tables where table_schema='数据库' ）
