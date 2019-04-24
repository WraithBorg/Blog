### 安装
```
// 安装  yum install redis;
// 启动   service redis start;
// 查看运行状态   service redis status;
// 查看redis进程    ps -ef | grep redis;
// 设置开机启动   chkconfig redis on;
// 进入本机redis服务  redis-cli
// 关闭redis   redis-cli -p 端口号 shutdown

```

#### redis 服务内命令
```
// 显示所有 key  keys *;
// 增     set key01 1000
// 删     del key01
// 改     set key01 999

// 查     get key01
// 查所有  keys *
// 查是否存在    exists key01
// 条件查询k开头的数据       keys k*

// 获取键到期的剩余时间   ttl key
// 指定过期时间2秒     expire key01 2
// 去除到期时间   persist key01
// 获取 key01,key02的value     mget key01 key02

// 设置key03 20秒到期，值为23232     setex key03 20 23232

// 退出 quit
```
