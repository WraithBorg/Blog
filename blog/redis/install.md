# Redis
#### docker 安装redis
```
docker search redis
docker pull docker.io/redis
docker run -p 6379:6379 -v $PWD/data:/data -d redis:latest redis-server --appendonly yes --requirepass "sdwdsb"
｛-v $PWD/data:/data : 将主机中当前目录下的data挂载到容器的/data｝
｛- d : 后台运行｝
｛redis-server --appendonly yes : 在容器执行redis-server启动命令，并打开redis持久化配置｝
// 连接、查看容器
docker exec -it 13c589ece0eb redis-cli
```
#### 登录redis(使用密码)
```
[root@test dockerdir]# docker exec -it 7cfd bash
root@7cfdf7c7c3ed:/data# redis-cli -h 127.0.0.1 -p 6379- -a sdwdsb
```
#### redis可视化工具（开源）
`getmedis.com`
