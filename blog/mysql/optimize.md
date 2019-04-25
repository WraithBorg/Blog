[TOC]
## 优化

### 优化子查询
	join不需要建立临时表，所以速度比子查询快

### 优化插入记录速度
影响插入速度因素：
>* 索引，唯一性校验，一次插入记录条数
>* 一条insert 插入多条记录 比多条insert速度要快
>* load data infile 批量导入数据 比insert语句快
	
### InnoDB引擎的表 常见优化
>* 禁用唯一性检查	
`插入数据之前 执行 set unique_checks= 0;
禁止对唯一索引的检查，数据导入完成之后在运行 set unique_checks= 1;对唯一索引的检查，数据导入完成之后在运行 set unique_checks= 1;`

>* 禁用外键检查
>* 禁止自动提交

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
2 rows in set (0.22 sec)
```
Op: 执行操作
Msg_type: 信息类型(status(状态),info(信息),note(注意),warning,error)
Msg_text: 显示信息

### 检查表
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
### 优化表
使用OPTIMIZE TABLE语句优化表，但只能优化VARCHAR BOLB TEXT 类型字段
OPTIMIZE [LOCAL | NO_WRITE_TO_BINLOG] TABLE table01,table02;
```
notice: InnoDB 表可能会执行报错
解决：
ALTER TABLE table.name ENGINE='InnoDB';  -- 转移表数据，去掉碎片直接优化，不过会影响业务
或者
在启动的时候指定--skip-new或者--safe-mode选项来支持optimize功能
然后在执行OPTIMEZE
```

#### show status
```
SHOW STATUS
    Connections 连接mysql服务器次数
    Uptime      mysql服务器上线时间
    Slow_queries 慢查询次数
    Com_select 查询操作次数
    Com_insert 插入操作次数
    Com_update 更新操作次数
    Com_delete 删除操作次数
e.g.
SHOW STATUS LIKE 'Slow_queries'
```

#### 性能瓶颈
CPU瓶颈
IOP瓶颈
网络瓶颈
用mpstat,iostat,sar,vmstat 查看系统性能状态









