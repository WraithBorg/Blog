####  下载jdk 和 tomcat

#### 根据Dockerfile创建镜像
+ 下载centos镜像
+ 下载jdk和tomcat并解压
+ 删除下载文件并重命名文件夹
+ 创建并编辑Dockerfile
+ 编译镜像
+ 启动镜像创建容器
``` 
[root@test soft]# docker pull centos
[root@test soft]# cd /usr/local/soft
[root@test soft]# wget https://download.oracle.com/otn/java/jdk/8u251-b08/3d5a2bb8f8d4428bbe94aed7ec7ae784/jdk-8u251-linux-x64.tar
[root@test soft]# wget http://ftp.meisei-u.ac.jp/mirror/apache/dist/tomcat/tomcat-8/v8.5.55/bin/apache-tomcat-8.5.55.tar.gz
[root@test soft]# tar zxvf jdk-8u251-linux-x64.tar
[root@test soft]# tar zxvf apache-tomcat-8.5.55.tar.gz
[root@test soft]# rm -rf jdk-8u251-linux-x64.tar
[root@test soft]# rm -rf apache-tomcat-8.5.55.tar.gz
[root@test soft]# mv jdk1.8.0_251 jdk8
[root@test soft]# mv apache-tomcat-8.5.55 tomcat85

[root@test soft]# vim Dockerfile
[root@test soft]# cat Dockerfile
FROM centos
MAINTAINER zxu
RUN mkdir -p /usr/local/soft
ADD jdk8 /usr/local/soft/jdk
ADD tomcat85 /usr/local/soft/tomcat
ENV JAVA_HOME /usr/local/soft/jdk
ENV CATALINA_HOME /usr/local/soft/tomcat
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/bin
EXPOSE 8084
CMD ["/usr/local/soft/tomcat/bin/catalina.sh","run"]
doc
[root@test soft]# docker build -t repostory/centos_tomcat .
[root@test soft]# docker run -v /usr/local/dockerdir/yun:/usr/local/soft/tomcat/webapps -d -p 8084:8080 --name Icentos repostory/centos_tomcat
[root@test soft]# docker ps
CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS              PORTS                    NAMES
9b8567ceed8d        repostory/centos_tomcat   "/usr/local/soft/tom…"   6 seconds ago       Up 4 seconds        0.0.0.0:8084->8080/tcp   Icentos
```
