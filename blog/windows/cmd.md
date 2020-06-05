### 查询端口占用
// 查看端口占用进程
netstat -aon | findstr "62607"
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

### 打开图形文件夹
```
1、打开当前目录 explorer .
2、打开上级目录 explorer ..
3、打开任意目录 explorer dirname
```

