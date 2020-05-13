# Docker
+ 镜像image
+ 容器container
+ 仓库repository

#### 启动docker
service docker start 

#### docker下载ubuntu镜像并测试
[root@test ~]# docker pull ubuntu:18.04
[root@test ~]# docker run -it ubuntu:18.04 bash
root@f8a6efef1bc4:/# echo "hello world~" 
hello world~
root@f8a6efef1bc4:/# exit 
exit

#### docker查看镜像
[root@test ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ubuntu              18.04               c3c304cb4f22        2 weeks ago         64.2MB
nginx               latest              602e111c06b6        2 weeks ago         127MB
[root@test ~]# docker image ls
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ubuntu              18.04               c3c304cb4f22        2 weeks ago         64.2MB
nginx               latest              602e111c06b6        2 weeks ago         127MB

#### docker查看所有容器
[root@test ~]# docker ps -a

#### docker删除容器和镜像
[root@test ~]# docker rm f8a6efef1bc4

[root@test ~]# docker rmi c3c304cb4f22

#### docker清理临时的遗留镜像文件层
[root@test ~]# docker image prune -f

#### 创建镜像三种方法
+ 基于已有镜像的容器创建
+ 基于本地模版导入
+ 基于Dockerfile创建

#### 查看docker配置信息
[root@test docker]# docker info

#### 进入容器目录 c27110b1378b容器id
docker exec -it c27110b1378b bash

## Dockerfile
#### 创建镜像
```
[root@test testdocker]# vim Dockerfile

FROM tomcat:7.0.88-jre8
MAINTAINER zxu <borg@gmail.com>
ENV TOMCAT_BASE /usr/local/tomcat
COPY ./sshdemo.war $TOMCAT_BASE/webapps/
```
#### 编译镜像
[root@test testdocker]# docker build -t sshdemo:1.0 .
#### 启动tomcat镜像
[root@test testdocker]# docker run -d -p 8080:8080 sshdemo:1.0

```
`http://www.ruanyifeng.com/blog/2018/02/docker-wordpress-tutorial.html`
`http://www.ruanyifeng.com/blog/2018/02/docker-tutorial.html`
# 查看docker信息
docker version
docker info
# 把用户(如root)加入 Docker 用户组
sudo usermod -aG docker root
# 查看docker进程
[root@test ~]# docker container ls
[root@test ~]# docker container ls -a

#  获取镜像元数据
docker container inspect [containerID]

# 启动，停止，查看日志
docker container start [containerID]
docker container stop
docker container logs [containerID]
docker kill [containerID]
Ctrl + d （或者输入 exit）退出容器
也可以用docker container kill终止容器运行
# 进入某个docker容器，进入容器
docker container exec -it [containerID] /bin/bash
# 将文件从docker容器拷贝到当前目录
docker container cp [containID]:[/path/to/file] .
# Dockerfile
---
FROM node:8.4
COPY . /app
WORKDIR /app
RUN npm install --registry=https://registry.npm.taobao.org
EXPOSE 3000
CMD node demos/01.js
---
RUN命令与CMD命令的区别在哪里？简单说，RUN命令在 image 文件的构建阶段执行，执行结果都会打包进入 image 文件；CMD命令则是在容器启动后执行。另外，一个 Dockerfile 可以包含多个RUN命令，但是只能有一个CMD命令。
```
## docker-compose 管理多个 Docker 容器组成一个应用
