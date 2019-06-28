### 查询端口占用
// 查看端口占用进程
netstat -aon | findstr "49690"
// 查看端口占用详情
tasklist|findstr "4840"

### 文件移动
// 清空文件夹 del c:\test\aaaa\*

### 关闭共享
net use
net use * /del 或 net use \\192.168.13.152\outsale-middleware /del /y
pause

### Tips
进入超级管理员终端 win+x a