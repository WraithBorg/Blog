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
