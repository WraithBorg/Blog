## CHECK TABLE

[Reference Document](https://dev.mysql.com/doc/refman/5.7/en/check-table.html)

```
mysql> CHECK TABLE titles,salaries;
+--------------------+-------+----------+----------+
| Table              | Op    | Msg_type | Msg_text |
+--------------------+-------+----------+----------+
| employees.titles   | check | status   | OK       |
| employees.salaries | check | status   | OK       |
+--------------------+-------+----------+----------+
2 rows in set (4.95 sec)
```