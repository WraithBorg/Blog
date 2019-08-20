## show status
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