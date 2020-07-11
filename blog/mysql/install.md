# Centos7 安装mysql
环境: CentOS 7.2.1511 x86_64	MySQL 版本: 5.7.16
```
// 下载
wget http://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.16-1.el7.x86_64.rpm-bundle.tar
// 解压
tar -xvf mysql-5.7.16-1.el7.x86_64.rpm-bundle.tar 
// 依次安装
1. rpm -ivh mysql-community-common-5.7.16-1.el7.x86_64.rpm

2. rpm -ivh mysql-community-libs-5.7.16-1.el7.x86_64.rpm
// ----{{{ 如果发生冲突
// 查看postfix和mariadb-libs
rpm -qa | grep postfix
rpm -qa | grep mariadb
// 卸载冲突
rpm -ev postfix-2.10.1-6.el7.x86_64
rpm -ev mariadb-libs-5.5.52-1.el7.x86_64
// 然后重新执行命令
// ----}}}

3. rpm -ivh mysql-community-client-5.7.16-1.el7.x86_64.rpm 

4. rpm -ivh mysql-community-server-5.7.16-1.el7.x86_64.rpm
// ----{{{ 如果发生冲突
wget http://mirror.centos.org/centos/6/os/x86_64/Packages/libaio-0.3.107-10.el6.x86_64.rpm
yum install net-tools
yum install libaio
// 然后重新执行命令
// ----}}}

```
### 启动mysql
```
// 为了保证数据库目录为与文件的所有者为 mysql 登陆用户，如果你是以 root 身份运行 mysql 服务，需要执行下面的命令初始化
mysqld --initialize --user=mysql
// 查看root 密码 Thanos
cat /var/log/mysqld.log 
// 启动数据库 并登陆
systemctl start mysqld.service
 mysql -uroot -p
 // 提示密码已过期
 show databases;
 // 重设密码
  ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';
```

### 安装两个mysql && 安装mysql解压版
1: 去掉path中上一个mysql的环境变量PATH
2: 停掉上一个mysql服务
3：修改解压版mysql default-my.int的port，不能为3306
4: CMD(管理员模式)进入bin目录 执行 $: mysqld install MYSQL56
5: bin目录 执行 $:mysqld –hlocalhost –uroot –p​
6:设置mysql密码
```
mysql免安装版如何设置root密码
mysqladmin -u root password "newpass"
如果root已经设置过密码，采用如下方法
mysqladmin -u root password oldpass "newpass"
```
7: mysql启动命命 $: mysqld --console
8: mysql关闭命令 $: mysqladmin -u root shutdown
