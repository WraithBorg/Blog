# Centos7命令

### 安装vim

```
rpm -qa|grep vim  ｛查询是否安装｝
yum -y install vim-enhanced ｛安装缺少的包｝
ls *.rpm; ｛显示所有rpm文件｝
pwd; {显示当前工作目录}
cd..; {切换上级目录}
whereis mysql; ｛查找相关文件｝
```

### 开放端口
关闭与开启防火墙
```
systemctl stop firewalld.service
systemctl start firewalld.service
```
查看防火墙是否开启的状态，以及开放端口的情况
```
systemctl status firewalld.service
sudo firewall-cmd --list-all
```
接下来通过以下命令开放http 80 端口：
```
sudo firewall-cmd --add-service=http --permanent
sudo firewall-cmd --add-port=80/tcp --permanent
```
命令末尾的--permanent表示用久有效，不加这句的话重启后刚才开放的端口就又失效了。
然后重启防火墙：
```
sudo firewall-cmd --reload
```
再次查看端口的开放情况：
```
sudo firewall-cmd --list-all
```
### 安装ShadowSocks
```
//1
yum -y install wget
//2
wget --no-check-certificate https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocksR.sh
//3
chmod +x shadowsocksR.sh
//4
./shadowsocksR.sh 2>&1 | tee shadowsocksR.log
```
## BASIC
```
切换为超级用户
su -
退出用户
exit
安装man手册
yum install man-pages
TAB 补全
查看当前所处目录 pwd 
```
#### chmod命令 权限详解
```
chomd644 xxx.ibd
// 从左至右，1-3位字符代表文件所有者的权限，4-6位字符代表同组用户的权限，7-9字符代表其他用户的权限。
读取的权限等于4，用r表示；写入的权限等于2，用w表示；执行的权限等于1，用x表示
444 r--r--r--
600 rw-------
644 rw-r--r--
666 rw-rw-rw-
700 rwx------
744 rwxr--r--
755 rwxr-xr-x
777 rwxrwxrwx
```

#### chown命令
```
[root@localhost ~]# chown -R  mysql:mysql ./
1: 命令格式：　chown [选项]... [所有者][:[组]] 文件...
2: chown将指定文件的拥有者改为指定的用户或组，用户可以是用户名或者用户ID；组可以是组名或者组ID；文件是以空格分开的要改变权限的文件列表，支持通配符
系统管理员经常使用chown命令，在将文件拷贝到另一个用户的名录下之后，让用户拥有使用该文件的权限。
3: 常见用法
[root@localhost ~]# chown [选项] 所有者：所属组文件或目录
【例 1】修改文件的所有者。
之所以需要修改文件的所有者，是因为赋予权限的需要。当普通用户需要对某个文件拥有最高权限的时候，是不能把其他人的权限修改为最高权限的，也就是不能出现 777 的权限，这是非常不安全的做法。
合理的做法是修改文件的所有者，这样既能让普通用户拥有最高权限，又不影响其他普通用户






```




