# Centos7命令

### BASIC
```
ls *.rpm; ｛显示所有rpm文件｝
pwd; {显示当前工作目录}
cd..; {切换上级目录}
whereis mysql; ｛查找相关文件｝
rm -rf 文件夹  {递归删除文件夹}
rm -rf *  {清空当前文件夹内所有文件}
clear {清除屏幕信息}
alias mysqll='mysql -uroot -pThanos'   {设置别名}
ip addr 显示网卡信息和ip地址
ll = ls -l
 cd - 命令可以回到上一个目录
```

### 安装vim
```
rpm -qa|grep vim  ｛查询是否安装｝
yum -y install vim-enhanced ｛安装缺少的包｝
```



### BASIC

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
### chmod命令 权限详解
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

### chown命令
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

### 端口占用解决办法
```
// 检查端口被哪个进程占用
[root@vultr local]# netstat -lnp|grep 80
tcp        0      0 0.0.0.0:80              0.0.0.0:*               LISTEN      23685/nginx: master
// 查看进程的详细信息
[root@vultr local]# ps 23685
  PID TTY      STAT   TIME COMMAND
23685 ?        Ss     0:00 nginx: master process ./nginx
// 杀掉进程
[root@vultr local]# kill -9 23685
```

#### find 文件查找
```
find    可以找到你想要的文件
格式：  find [目录] [选项] [选项值]
选项：怎么找
    -name   按照名字找
        可以使用通配符
    -size   按照大小找
        单位为  kmg   10k（等于10k）   +10k（大于10k）   -10k（小于10k）
    -user   按照用户名
    -group  按照组名
    -maxdepth  -mindepth   限制查找的目录层级，默认递归查找所有
    -ctime  按照创建时间查找
        单位是天
选项值：找什么
    find / -name demo.txt
    find / -name \*.txt
    find / -size +10k
    find / -user demo.txt
    find / -group demo.txt
    find / -mindepth 4 -name \*.txt
    find / -mindepth 3 -maxdepth 5 -name \*.txt
e.g.
find *.cfg
```

#### 文件内容搜索
```
grep   查找的内容   文件路径

选项
    --color=auto   将颜色高亮显示
        给 grep 指令起一个别名   vi ~/.bashrc
        添加一行     alias grep='grep --color=auto'
        让配置文件立即生效       source ~/.bashrc
    -c         得到内容的个数
    -i         不区分大小写的查找
    -n         显示在文档中的行号
    -r         递归查找，但是不能限制后缀，只能遍历所有
        grep -r that ~/*
    -l         只显示文件名，不显示内容

e.g.
grep movie demo.txt
grep that ~/*.txt
grep -l 你好 ~/test/*.txt
```

### path
```
~代表用户的/home/用户明目录
假设用户名是x，那么~/就是/home/x/

.是代表此目录本身，但是一般可以不写
cd ~/. 和cd ~ 和cd ~/效果一样

如果.后面有东西又是另外一种情况，点在文件名头部，代表一个隐藏文件
~/.local是你的主目录下一个.local的文件夹的路径，
并且从.可以看出，这是一个饮藏文件，
如果不用ls -a的话，一般ls是无法看到的

/ 是根节点， ~ 是 home
如果以root账号登陆
~ 是 /root/
/ 是 /

如果以 name 登陆
~ 是 /home/name/
/ 是 /
```

### 返回到根目录
cd /

#### 删除文件夹xxx
rm -rf xxx

#### 创建文件夹xxx
mkdir -p xxx

#### linux 普通用户切换root提示用户不存在的解决办法
su - root -s /bin/bash

#### 查看内核
[root@test ~]# uname -sr
Linux 5.6.12-1.el7.elrepo.x86_64

#### CentOS 查看版本哈
[root@test sinatra]# rpm -q centos-release
centos-release-7-8.2003.0.el7.centos.x86_64
[root@test sinatra]# cat /etc/redhat-release
CentOS Linux release 7.8.2003 (Core)

#### 安装图形界面
```
yum groupinstall "GNOME Desktop" "Graphical Administration Tools"
```
#### 设置图形界面
```
systemctl get-default #获取当前系统运行形式，会显示multi-user.target（命令行终端），或者：graphical.target
systemctl set-default graphical.target #设置默认启动为图形界面，reboot后界面会自动是图形窗口了。
systemctl set-default multi-user.target #换回命令界面启动
```
#### 文件搜索
```
whereis 命令名
#搜索命令所在路径及帮助文档所在位置
选项：
 -b :只查找可执行文件位置
 -m:只查找帮助文件
[root@localhost ~]# whereis ls

ls: /usr/bin/ls /usr/share/man/man1/ls.1.gz
[root@localhost ~]# whereis -b ls
ls: /usr/bin/ls
[root@localhost ~]# whereis -m ls
ls: /usr/share/man/man1/ls.1.gz
我们可以查到ls命令的位置以及帮助文档的位置

which 文件名
搜索命令所在路径及别名
[root@localhost ~]# which ls
alias ls='ls --color=auto'
/usr/bin/ls
相比 ，多了个别名；
```
#### 添加软链接
```
ln -s /usr/bin/google-chrome-stable /bin/chrome
```
#### 查看当前软件的安装信息
sudo yum info google-chrome-stable

#### 创建并进入文件夹目录
[root@test local]# mkdir nginx-docker-demo && cd nginx-docker-demo

#### 将文件夹A重命名为B
mv A B

#### 搜索包含某个文本的文件
[root@test local]# grep -r "213sss" gitspace
gitspace/Demo.class:213sss

#### 搜索文件
```
grep -r "查询内容"  文件目录
grep -r -l "查询内容" 
  ##根据时间查找日志
  grep '2020-02-27 17:5[6,9]' xinyar-erp-auto.log

  ##查询指定时间段内的日志

  eg、grep -E '2020-02-27 14:5[5-9]|2020-02-28 15:0[0-5]' xinyar-erp-auto.log

  ##查找关键字
  grep -C 10 'aaaa' nohup.out

##文件名+内容  
find 文件目录  -type f |xargs grep "查询内容"; 

eg：
grep -r "version.app.xinyartech.com"  /data/nginx/conf.d
grep -r -l "version.app.xinyartech.com"  /data/nginx/conf.d
find /data/nginx/conf.d  -type f |xargs grep "version.app.xinyartech.com"; 

```

```
查看文件 # cat A.txt
复制文件 # cp A.txt AA.txt
更改文件权限 # sudo chmod 777 AA.txt
更改群组 # chgrp 【groupname】 A.txt
更改文件所有者 # chown 【username】 A.txt
字符串搜索 # # grep 【keyword】 A.txt
```
```
文件权限
读4，写2，执行1
文件所有者，群组用户，其他用户
```

#### 查看linux查看硬件
```
cat /proc/cpuinfo | grep name | cut -f2 -d: |uniq -c
cat /proc/meminfo | grep MemTotal
df -hl 
```

#### 复制文件快捷键
[root@test conf]# cp /usr/local/soft/tomcat85/conf/server.xml{,.bak}

#### 复制完文件后，查看文件目录
[root@test /]# cp /usr/local/soft/tomcat85/conf/server.xml{,.bak}
[root@test /]# ls !$:h
等同于 ls /usr/local/soft/tomcat85/conf

#### 查看历史命令行,并执行第N条命令
[root@test bin]# history
[root@test bin]# !1075

#### 设置jdk环境变量
export JAVA_HOME=/usr/local/soft/jdk8
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
source /etc/profile
