### 查询端口占用
// 查看端口占用进程
netstat -aon | findstr "49690"
// 查看端口占用详情
tasklist|findstr "4840"