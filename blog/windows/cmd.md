### 查询端口占用
// 查看端口占用进程
netstat -aon | findstr "62607"
// 查看端口占用详情
tasklist|findstr "4840"

### 文件移动
C:\Users\Administrator\Desktop\vpn>move client.zip \\192.168.13.147\rootdir
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

### 打包程序
```
cd c:\zxu_gitspace\cld
call mvn install
call cd c:\zxu_gitspace\cld\target
call rename cld-0.0.1-SNAPSHOT.war cld.war
call explorer .
```

### 删除共享文件夹缓存账号密码
查看缓存的ip和密码
C:\Users\Administrator>net use
删除特定的缓存账号
net use \\192.168.10.100 /delete
删除所有的缓存账号
C:\Users\Administrator>net use * /delete
